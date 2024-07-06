package com.xiaobingkj.giteer.ui.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.databinding.ActivityLoginBinding;
import com.xiaobingkj.giteer.singleton.Giteer;
import com.xiaobingkj.giteer.ui.webview.WebViewActivity;

import rxhttp.RxHttp;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ImmersionBar.with(this)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .init();

        binding.oauthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });

        binding.personalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(LoginActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("");
                builder.setMessage("Privacy statement: giteer will not collect any information from your gitee account. Please rest assured, use loginButton to get info from private repository");

                builder.setView(input);

                // 设置肯定按钮
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击确认按钮的操作
                        String inputString = input.getText().toString();
                        if (inputString.isEmpty()) {
                            return;
                        }
                        String tokenJson = "{\"access_token\":"+"\"" + inputString + "\"}";
                        Giteer.getInstance().setToken(tokenJson);
                        RxHttp.get("api/v5/user")
                                .toObservableString()
                                .subscribe( s2 -> {
                                    Giteer.getInstance().setUser(s2);
                                    finish();
                                }, throwable -> {

                                });
                    }
                });

                // 设置否定按钮
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击取消按钮的操作
                    }
                });

                // 创建对话框
                AlertDialog alertDialog = builder.create();

                // 显示对话框
                alertDialog.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String token = Giteer.getInstance().getToken().getAccess_token();
        if (token != null && !token.isEmpty()) {
            finish();
        }
    }
}