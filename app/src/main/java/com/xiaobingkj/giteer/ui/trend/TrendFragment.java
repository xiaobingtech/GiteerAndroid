package com.xiaobingkj.giteer.ui.trend;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayoutMediator;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.databinding.FragmentTrendBinding;
import com.xiaobingkj.giteer.listener.TokenChangeListener;

public class TrendFragment extends Fragment {

    private FragmentTrendBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_trend, container, false);
        View root = binding.getRoot();

        TrendFragmentStateAdapter adapter = new TrendFragmentStateAdapter(this);
        adapter.addFragment(TrendSubFragment.newInstance("featured/"));
        adapter.addFragment(TrendSubFragment.newInstance("popular/"));
        adapter.addFragment(TrendSubFragment.newInstance("latest/"));

        binding.viewPager.setAdapter(adapter);

        new TabLayoutMediator(binding.tabs, binding.viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText(getString(R.string.trend_recommend));
                    break;
                case 1:
                    tab.setText(getString(R.string.trend_hot));
                    break;
                case 2:
                    tab.setText(getString(R.string.trend_latest));
                    break;
                default:
                    // 默认情况，虽然实际上我们已经覆盖了所有情况
                    break;
            }
        }).attach();

        return root;
    }

}