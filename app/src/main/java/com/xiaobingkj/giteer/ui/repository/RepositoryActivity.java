package com.xiaobingkj.giteer.ui.repository;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.databinding.ActivityRepositoryBinding;
import com.xiaobingkj.giteer.databinding.ActivityWebViewBinding;
import com.xiaobingkj.giteer.utils.QMUIUtils;

public class RepositoryActivity extends AppCompatActivity {

    private ActivityRepositoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRepositoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        QMUIUtils.showToolBar(binding.topbar, new QMUIUtils.QmBackOnclickListener() {
            @Override
            public void onBackClick() {
                finish();
            }
        }, this, "");
    }
}