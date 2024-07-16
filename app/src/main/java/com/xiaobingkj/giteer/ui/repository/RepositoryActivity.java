package com.xiaobingkj.giteer.ui.repository;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spanned;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.blankj.utilcode.util.EncodeUtils;
import com.bumptech.glide.Glide;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.databinding.ActivityRepositoryBinding;
import com.xiaobingkj.giteer.databinding.ActivityWebViewBinding;
import com.xiaobingkj.giteer.entry.ReadMeEntry;
import com.xiaobingkj.giteer.entry.StarEntry;
import com.xiaobingkj.giteer.entry.TrendSubEntry;
import com.xiaobingkj.giteer.utils.QMUIUtils;

import org.commonmark.node.Node;

import cn.carbs.android.avatarimageview.library.AvatarImageView;
import io.noties.markwon.Markwon;
import io.noties.markwon.ext.tables.TablePlugin;
import io.noties.markwon.image.glide.GlideImagesPlugin;
import io.noties.markwon.syntax.SyntaxHighlight;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

public class RepositoryActivity extends AppCompatActivity {

    private ActivityRepositoryBinding binding;
    private String name;
    private String fullName;
    private String ref = "main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRepositoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.repo), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            StarEntry entry = getIntent().getSerializableExtra("model", StarEntry.class);
            if (entry == null) {
                TrendSubEntry subEntry = getIntent().getSerializableExtra("v3model", TrendSubEntry.class);
                name = subEntry.getName_with_namespace();
                fullName = subEntry.getPath_with_namespace();
                ref = subEntry.getDefault_branch();
                AvatarImageView avatar = binding.avatar;
                if (subEntry.getOwner().getPortrait_url().contains("no_portrait.png")) {
                    avatar.setTextAndColor(subEntry.getOwner().getName().substring(0, 1), R.color.gray);
                }else{
                    Glide.with(this).load(subEntry.getOwner().getPortrait_url()).into(avatar);
                }
                binding.name.setText(name);
                binding.desc.setText(subEntry.getDescription());
                binding.time.setText(subEntry.getLast_push_at());
                refreshReadMe(subEntry.getPath_with_namespace());
            }else{
                name = entry.getHuman_name();
                fullName = entry.getFull_name();
                ref = entry.getDefault_branch();
                AvatarImageView avatar = binding.avatar;
                if (entry.getOwner().getAvatar_url().equals("https://gitee.com/assets/no_portrait.png")) {
                    avatar.setTextAndColor(entry.getOwner().getName().substring(0, 1), R.color.gray);
                }else{
                    Glide.with(this).load(entry.getOwner().getAvatar_url()).into(avatar);
                }
                binding.name.setText(name);
                binding.desc.setText(entry.getDescription());
                binding.time.setText(entry.getUpdated_at());
                refreshReadMe(entry.getFull_name());
            }



        }else{

        }

        QMUIUtils.showToolBar(binding.topbar, new QMUIUtils.QmBackOnclickListener() {
            @Override
            public void onBackClick() {
                finish();
            }
        }, this, name);

        binding.viewClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RepositoryActivity.this, RepositoryTreeActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("fullName", fullName);
                intent.putExtra("ref", ref);
                startActivity(intent);
            }
        });
    }

    void refreshReadMe(String name) {
        RxHttp.get("api/v5/repos/"+name+"/readme")
                .add("ref", ref)
                .toObservable(ReadMeEntry.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( s -> {

                    final Markwon markwon = Markwon.builder(this)
                            .usePlugin(GlideImagesPlugin.create(this))
                            .usePlugin(TablePlugin.create(this))
                            .build();

                    String content = new String(EncodeUtils.base64Decode(s.getContent()));
                    // 假设这是你的基础URL
                    String baseUrl = "https://gitee.com/"+name+"/raw/"+ref+"/";


                    // 正则表达式匹配Markdown中的图片链接
                    // 假设所有的相对路径都不以"http"或"https"开头
                    String regex = "\\]\\((?!http)(.*?)\\)";

                    // 替换相对路径为绝对路径
                    String replacedContent = content.replaceAll(regex, "](<" + baseUrl + "$1>)");

                    // parse markdown to commonmark-java Node
                    final Node node = markwon.parse(replacedContent);

                    // create styled text from parsed Node
                    final Spanned markdown = markwon.render(node);

                    // use it on a TextView
                    markwon.setParsedMarkdown(binding.md, markdown);
                });
    }
}