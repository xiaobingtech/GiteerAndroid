package com.xiaobingkj.giteer.ui.search;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayoutMediator;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.listener.SearchKeyChangeListener;
import com.xiaobingkj.giteer.listener.TokenChangeListener;
import com.xiaobingkj.giteer.databinding.FragmentSearchBinding;
import com.xiaobingkj.giteer.ui.trend.TrendFragmentStateAdapter;
import com.xiaobingkj.giteer.ui.trend.TrendSubFragment;

public class SearchFragment extends Fragment{

    private FragmentSearchBinding binding;
    private SearchFragmentStateAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_search, container, false);
        View root = binding.getRoot();

        adapter = new SearchFragmentStateAdapter(this);
        adapter.addFragment(new SearchRepoSubFragment());
        adapter.addFragment(new SearchUserSubFragment());

        binding.viewPager.setAdapter(adapter);

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SearchRepoSubFragment repoSubFragment = (SearchRepoSubFragment) adapter.createFragment(0);
                repoSubFragment.onKeyChanged(s.toString());
                SearchUserSubFragment userSubFragment = (SearchUserSubFragment) adapter.createFragment(1);
                userSubFragment.onKeyChanged(s.toString());
            }
        });

        new TabLayoutMediator(binding.tabs, binding.viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText(getString(R.string.search_repo));
                    break;
                case 1:
                    tab.setText(getString(R.string.search_user));
                    break;
                default:
                    // 默认情况，虽然实际上我们已经覆盖了所有情况
                    break;
            }
        }).attach();

        return root;
    }
}