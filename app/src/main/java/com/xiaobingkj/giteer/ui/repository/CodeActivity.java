package com.xiaobingkj.giteer.ui.repository;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.databinding.ActivityCodeBinding;
import com.xiaobingkj.giteer.utils.QMUIUtils;

import io.github.rosemoe.sora.widget.CodeEditor;

public class CodeActivity extends AppCompatActivity {

    private ActivityCodeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityCodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.code), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        QMUIUtils.showToolBar(binding.topbar, new QMUIUtils.QmBackOnclickListener() {
            @Override
            public void onBackClick() {
                finish();
            }
        }, this, "文件详情");

        binding.editor.setText("Hello world");
        binding.editor.setTypefaceText(Typeface.MONOSPACE); // 使用Monospace字体
        binding.editor.setNonPrintablePaintingFlags(
                CodeEditor.FLAG_DRAW_WHITESPACE_LEADING | CodeEditor.FLAG_DRAW_LINE_SEPARATOR | CodeEditor.FLAG_DRAW_WHITESPACE_IN_SELECTION); // Show Non-Printable Characters
    }
}