package com.xiaobingkj.giteer.ui.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.databinding.ActivityWebViewBinding;
import com.xiaobingkj.giteer.entry.OAuthEntry;
import com.xiaobingkj.giteer.entry.UserEntry;
import com.xiaobingkj.giteer.singleton.Giteer;

import rxhttp.RxHttp;


public class WebViewActivity extends AppCompatActivity {

    public static final String TAG = "WebViewActivity";

    private ActivityWebViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWebViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ImmersionBar.with(this)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true)
                .init();

        String client_id = "";
        String client_secret = "";
        String redirect_uri = "";

        String url = "https://gitee.com/oauth/authorize?client_id=" + client_id + "&redirect_uri=" + redirect_uri + "&response_type=code";
        Log.d(TAG, "打开URL:"+url);
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
                            .add("client_id", client_id)
                            .add("client_secret", client_secret)
                            .add("redirect_uri", redirect_uri)
                            .add("grant_type", "authorization_code")
                            .toObservable(OAuthEntry.class)
                            .subscribe( s -> {
                                String token = s.getAccess_token();
                                Log.d(TAG, "最新Token:" + token);
                                Giteer.getInstance().setToken(new Gson().toJson(s));
                                RxHttp.get("api/v5/user")
                                        .toObservableString()
                                        .subscribe( s2 -> {
                                            Giteer.getInstance().setUser(s2);
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