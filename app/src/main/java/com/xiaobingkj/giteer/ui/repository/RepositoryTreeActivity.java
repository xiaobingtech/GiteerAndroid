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

import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.databinding.ActivityRepositoryBinding;
import com.xiaobingkj.giteer.databinding.ActivityRepositoryTreeBinding;
import com.xiaobingkj.giteer.entry.ReadMeEntry;
import com.xiaobingkj.giteer.entry.RepoTreeEntry;
import com.xiaobingkj.giteer.ui.code.MainCodeActivity;
import com.xiaobingkj.giteer.utils.QMUIUtils;

import java.util.ArrayList;
import java.util.List;

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
                //打开这个文件
//                Intent intent = new Intent(RepositoryTreeActivity.this, CodeActivity.class);
//                intent.putExtra("url", item.downloadUrl);
//                startActivity(intent);

                startActivity(new Intent(RepositoryTreeActivity.this, MainCodeActivity.class));
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