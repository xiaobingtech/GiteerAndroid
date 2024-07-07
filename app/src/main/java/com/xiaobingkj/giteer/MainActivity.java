package com.xiaobingkj.giteer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayoutMediator;
import com.xiaobingkj.giteer.databinding.ActivityMainBinding;
import com.xiaobingkj.giteer.listener.LoginChangeListener;
import com.xiaobingkj.giteer.singleton.Giteer;
import com.xiaobingkj.giteer.ui.event.EventFragment;
import com.xiaobingkj.giteer.ui.login.LoginActivity;
import com.xiaobingkj.giteer.ui.me.MeFragment;
import com.xiaobingkj.giteer.ui.search.SearchFragment;
import com.xiaobingkj.giteer.ui.star.StarFragment;
import com.xiaobingkj.giteer.ui.trend.TrendFragment;

import rxhttp.RxHttp;

public class MainActivity extends AppCompatActivity implements LoginChangeListener {

    public static final String TAG = "MainActivity";

    private ActivityMainBinding binding;

    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //查看Token判断登录状态
        String token = Giteer.getInstance().getToken().getAccess_token();
        if (token == null || token.length() == 0) {
            //跳转登录页面
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }else{
//            https://gitee.com/oauth/token?grant_type=refresh_token&refresh_token={refresh_token}
            RxHttp.postJson("oauth/token")
                    .add("grant_type", "refresh_token")
                    .add("refresh_token", Giteer.getInstance().getToken().getRefresh_token())
                    .toObservableString()
                    .subscribe(s -> {
                        Log.d(TAG, "每次进入会刷新Token");
                        Log.d(TAG, s);
                        Giteer.getInstance().setToken(s);
                        ViewPageAdapter adapter = (ViewPageAdapter) binding.viewPager.getAdapter();
                        adapter.eventFragment.onTokenChanged();
                        adapter.starFragment.onTokenChanged();
                        adapter.searchFragment.onTokenChanged();
                        adapter.trendFragment.onTokenChanged();
                        adapter.meFragment.onTokenChanged();
                    }, throwable -> {

                    });
        }

        //Tabbar
        binding.viewPager.setAdapter(new ViewPageAdapter(this));
        binding.viewPager.setUserInputEnabled(false);

        new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("动态");
                    break;
                case 1:
                    tab.setText("星标");
                    break;
                case 2:
                    tab.setText("搜索");
                    break;
                case 3:
                    tab.setText("趋势");
                    break;
                case 4:
                    tab.setText("我");
                    break;
                default:
                    // 默认情况，虽然实际上我们已经覆盖了所有情况
                    break;
            }
        }).attach();

    }

    @Override
    public void logout() {
        Giteer.getInstance().setToken("{\"access_token\":\"\"}");
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private static class ViewPageAdapter extends FragmentStateAdapter {

        private EventFragment eventFragment = new EventFragment();
        private StarFragment starFragment = new StarFragment();
        private SearchFragment searchFragment = new SearchFragment();
        private TrendFragment trendFragment = new TrendFragment();
        private MeFragment meFragment = new MeFragment();

        public ViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @Override
        public int getItemCount() {
            return 5;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return this.eventFragment;
                case 1:
                    return this.starFragment;
                case 2:
                    return this.searchFragment;
                case 3:
                    return this.trendFragment;
                case 4:
                    return this.meFragment;
                default:
                    return new Fragment();
            }

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}