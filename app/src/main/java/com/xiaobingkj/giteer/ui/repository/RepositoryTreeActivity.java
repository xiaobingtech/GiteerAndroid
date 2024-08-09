package com.xiaobingkj.giteer.ui.repository;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.blankj.utilcode.util.EncodeUtils;
import com.blankj.utilcode.util.StringUtils;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.databinding.ActivityRepositoryBinding;
import com.xiaobingkj.giteer.databinding.ActivityRepositoryTreeBinding;
import com.xiaobingkj.giteer.entry.ReadMeEntry;
import com.xiaobingkj.giteer.entry.RepoTreeEntry;
import com.xiaobingkj.giteer.utils.QMUIUtils;

import java.util.ArrayList;
import java.util.List;

import cc.shinichi.library.ImagePreview;
import cc.shinichi.library.bean.ImageInfo;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import rxhttp.RxHttp;

public class RepositoryTreeActivity extends AppCompatActivity {

    private ActivityRepositoryTreeBinding binding;

    private AndroidTreeView tView;

    private String TAG = "RepositoryTreeActivity";

    private String name;
    private String fullName;

    private String path = "";
    private String ref = "main";

    private TreeNode rootNode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRepositoryTreeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        name = getIntent().getStringExtra("name");
        fullName = getIntent().getStringExtra("fullName");
        ref = getIntent().getStringExtra("ref");

        QMUIUtils.showToolBar(binding.topbar, new QMUIUtils.QmBackOnclickListener() {
            @Override
            public void onBackClick() {
                finish();
            }
        }, this, name);

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tree), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rootNode = TreeNode.root();

        tView = new AndroidTreeView(this, rootNode);
        tView.setDefaultAnimation(true);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
        tView.setDefaultViewHolder(IconTreeItemHolder.class);
        tView.setDefaultNodeClickListener(nodeClickListener);
        tView.setDefaultNodeLongClickListener(nodeLongClickListener);
        ViewGroup containerView = (ViewGroup)binding.container;
        containerView.addView(tView.getView());

        if (savedInstanceState != null) {
            String state = savedInstanceState.getString("tState");
            if (!TextUtils.isEmpty(state)) {
                tView.restoreState(state);
            }
        }

        requestData();
    }

    private void requestData() {
        RxHttp.get("api/v5/repos/"+fullName+"/contents/"+path)
                .add("ref", ref)
                .toObservableList(RepoTreeEntry.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( s->{
                    for (RepoTreeEntry repoTreeEntry : s) {
                        if (repoTreeEntry.getType().equals("dir")) {
                            tView.addNode(rootNode, new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, repoTreeEntry.getName(), repoTreeEntry.getPath(), repoTreeEntry.getDownload_url())));
                        }else{
                            tView.addNode(rootNode, new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_drive_file, repoTreeEntry.getName(), repoTreeEntry.getPath(), repoTreeEntry.getDownload_url())));
                        }
                    }
                });
    }

    private void requestFoldData(String path, TreeNode currentNode) {
        if (currentNode.getChildren().size() == 0){
            RxHttp.get("api/v5/repos/"+fullName+"/contents/"+path)
                    .add("ref", ref)
                    .toObservableList(RepoTreeEntry.class)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( s->{
                        for (RepoTreeEntry repoTreeEntry : s) {
                            if (repoTreeEntry.getType().equals("dir")) {
                                tView.addNode(currentNode, new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_folder, repoTreeEntry.getName(), repoTreeEntry.getPath(), repoTreeEntry.getDownload_url())));
                            }else{
                                tView.addNode(currentNode, new TreeNode(new IconTreeItemHolder.IconTreeItem(R.string.ic_drive_file, repoTreeEntry.getName(), repoTreeEntry.getPath(), repoTreeEntry.getDownload_url())));
                            }
                            currentNode.setExpanded(true);
                        }
                    });
        }else{
            currentNode.setSelected(false);
        }

    }

    private TreeNode.TreeNodeClickListener nodeClickListener = new TreeNode.TreeNodeClickListener() {
        @Override
        public void onClick(TreeNode node, Object value) {
            IconTreeItemHolder.IconTreeItem item = (IconTreeItemHolder.IconTreeItem) value;
            if (item.icon == R.string.ic_folder) {
                requestFoldData(item.path, node);
            }else{

                String[] parts = item.downloadUrl.split("/");
                String lastPart = parts[parts.length - 1];
                String encodeUrl = EncodeUtils.urlEncode(lastPart);
                parts[parts.length - 1] = encodeUrl;
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < parts.length; i++) {
                    builder.append(parts[i]);
                    if (i < parts.length - 1) {
                        builder.append("/");
                    }
                }
                String joinedString = builder.toString();
                if (item.path.endsWith("jpg") || item.path.endsWith("jpeg") || item.path.endsWith("png") || item.path.endsWith("webp") || item.path.endsWith("gif")) {
                    //查看图片
                    ImageInfo imageInfo;
                    final List<ImageInfo> imageInfoList = new ArrayList<>();
                    imageInfo = new ImageInfo();
                    imageInfo.setOriginUrl(joinedString);// 原图url
                    imageInfo.setThumbnailUrl(joinedString);// 缩略图url
                    imageInfoList.add(imageInfo);

                    ImagePreview
                            .getInstance()
                            // 上下文，必须是activity，不需要担心内存泄漏，本框架已经处理好；
                            .setContext(RepositoryTreeActivity.this)

                            // 设置从第几张开始看（索引从0开始）
                            .setIndex(0)

                            //=================================================================================================
                            // 有三种设置数据集合的方式，根据自己的需求进行三选一：
                            // 1：第一步生成的imageInfo List
                            .setImageInfoList(imageInfoList)

                            // 2：直接传url List
                            //.setImageList(List<String> imageList)

                            // 3：只有一张图片的情况，可以直接传入这张图片的url
                            //.setImage(String image)
                            //=================================================================================================

                            // 开启预览
                            .start();
                }else if(item.path.endsWith("mp4")){
                    //查看视频
                    Intent intent = new Intent(RepositoryTreeActivity.this, VideoActivity.class);
                    intent.putExtra("url", joinedString);
                    startActivity(intent);
                }else{
                    //打开这个代码文件
                    Intent intent = new Intent(RepositoryTreeActivity.this, CodeActivity.class);
                    intent.putExtra("url", joinedString);
                    startActivity(intent);
                }

            }
        }
    };

    private TreeNode.TreeNodeLongClickListener nodeLongClickListener = new TreeNode.TreeNodeLongClickListener() {
        @Override
        public boolean onLongClick(TreeNode node, Object value) {
            IconTreeItemHolder.IconTreeItem item = (IconTreeItemHolder.IconTreeItem) value;
            return true;
        }
    };
}