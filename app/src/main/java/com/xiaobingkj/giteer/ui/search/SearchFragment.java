package com.xiaobingkj.giteer.ui.search;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.listener.TokenChangeListener;
import com.xiaobingkj.giteer.databinding.FragmentSearchBinding;

public class SearchFragment extends Fragment implements TokenChangeListener {

    private FragmentSearchBinding binding;

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
        return root;
    }

    @Override
    public void onTokenChanged() {

    }
}