package com.xiaobingkj.giteer.ui.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.xiaobingkj.giteer.BaseActivity;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.constant.Constants;
import com.xiaobingkj.giteer.databinding.ActivityLoginBinding;
import com.xiaobingkj.giteer.singleton.Giteer;
import com.xiaobingkj.giteer.ui.webview.WebViewActivity;

import rxhttp.RxHttp;

public class LoginActivity extends BaseActivity {

    public static final String TAG = "LoginActivity";

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.oauthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String url = "https://gitee.com/oauth/authorize?client_id=" + Constants.client_id + "&redirect_uri=" + Constants.redirect_uri + "&response_type=code";
                Log.d(TAG, "打开URL:"+url);

                Intent intent = new Intent(LoginActivity.this, WebViewActivity.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

        binding.personalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input = new EditText(LoginActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("");
                builder.setMessage("隐私声明：Giteer不会从您的Gitee账号收集任何信息，请您放心使用，如果需要访问私有仓库请点击底部登录按钮进行OAuth授权");

                builder.setView(input);

                // 设置肯定按钮
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
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
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
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