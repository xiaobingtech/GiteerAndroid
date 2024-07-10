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
import com.xiaobingkj.giteer.entry.StarEntry;
import com.xiaobingkj.giteer.utils.QMUIUtils;

public class RepositoryActivity extends AppCompatActivity {

    private ActivityRepositoryBinding binding;
    private String name;

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
            name = entry.getHuman_name();
        }

        QMUIUtils.showToolBar(binding.topbar, new QMUIUtils.QmBackOnclickListener() {
            @Override
            public void onBackClick() {
                finish();
            }
        }, this, name);
    }
}