package com.xiaobingkj.giteer.ui.webview;

import static com.xiaobingkj.giteer.singleton.Giteer.getInstance;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.xiaobingkj.giteer.MainActivity;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.constant.Constants;
import com.xiaobingkj.giteer.databinding.ActivityWebViewBinding;
import com.xiaobingkj.giteer.entry.OAuthEntry;
import com.xiaobingkj.giteer.entry.UserEntry;
import com.xiaobingkj.giteer.singleton.Giteer;
import com.xiaobingkj.giteer.ui.login.LoginActivity;
import com.xiaobingkj.giteer.utils.QMUIUtils;

import rxhttp.RxHttp;


public class WebViewActivity extends AppCompatActivity {

    public static final String TAG = "WebViewActivity";

    private ActivityWebViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWebViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.web), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        QMUIUtils.showToolBar(binding.topbar, new QMUIUtils.QmBackOnclickListener() {
            @Override
            public void onBackClick() {
                finish();
            }
        }, this, "");

        String url = getIntent().getStringExtra("url");

        binding.webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {

                String url = webResourceRequest.getUrl().toString();
                Log.d(TAG, "当前URL:"+url);
                if (url.contains("code=")){
                    //拿到Code
                    String[] strings = url.split("code=");
                    String code = strings[strings.length - 1];
                    RxHttp.postJson("oauth/token")
                            .add("code", code)
                            .add("client_id", Constants.client_id)
                            .add("client_secret", Constants.client_secret)
                            .add("redirect_uri", Constants.redirect_uri)
                            .add("grant_type", "authorization_code")
                            .toObservable(OAuthEntry.class)
                            .subscribe( s -> {
                                String token = s.getAccess_token();
                                Log.d(TAG, "最新Token:" + token);
                                getInstance().setToken(new Gson().toJson(s));
                                RxHttp.get("api/v5/user")
                                        .toObservableString()
                                        .subscribe( s2 -> {
                                            getInstance().setUser(s2);
                                            Intent intent = new Intent(WebViewActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }, throwable -> {

                                        });
                            }, throwable -> {

                            });
                }

                return super.shouldOverrideUrlLoading(webView, webResourceRequest);
            }
        });
        Log.d(TAG, "是否是X5内核:" + binding.webview.getIsX5Core());
        WebSettings settings = binding.webview.getSettings();
        settings.setJavaScriptEnabled(true);
        //进入授权页面
        binding.webview.loadUrl(url);
    }
}