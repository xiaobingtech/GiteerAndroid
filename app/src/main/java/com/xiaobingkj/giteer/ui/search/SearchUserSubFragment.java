package com.xiaobingkj.giteer.ui.search;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter4.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.xiaobingkj.giteer.MainActivity;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.constant.Constants;
import com.xiaobingkj.giteer.databinding.FragmentSearchRepoSubBinding;
import com.xiaobingkj.giteer.databinding.FragmentSearchUserSubBinding;
import com.xiaobingkj.giteer.entry.ApiException;
import com.xiaobingkj.giteer.entry.ErrorResponse;
import com.xiaobingkj.giteer.entry.MeEntry;
import com.xiaobingkj.giteer.entry.StarEntry;
import com.xiaobingkj.giteer.listener.SearchKeyChangeListener;
import com.xiaobingkj.giteer.ui.repository.RepositoryActivity;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.ranges.IntRange;
import rxhttp.RxHttp;
import rxhttp.wrapper.exception.HttpStatusCodeException;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SearchUserSubFragment extends Fragment implements SearchKeyChangeListener {

    public static final String TAG = "SearchSubFragment";

    private FragmentSearchUserSubBinding binding;

    private String key;

    private Integer page = 1;
    private SearchUserSubListAdapter adapter;

    public SearchUserSubFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_user_sub, container, false);
        View root = binding.getRoot();

        binding.listView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new SearchUserSubListAdapter();
        binding.listView.setAdapter(adapter);

        binding.refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                headerRefresh();
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                footerRefresh();
            }

        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener<MeEntry>() {
            @Override
            public void onClick(@NonNull BaseQuickAdapter<MeEntry, ?> baseQuickAdapter, @NonNull View view, int i) {
//                Toast.makeText(getActivity(), "点击了" + i, Toast.LENGTH_SHORT).show();

            }
        });

        return root;
    }

    private void headerRefresh() {
        page = 1;
        requestData();
    }

    private void footerRefresh() {
        page = page + 1;
        requestData();
    }

    public void requestData() {
        Log.d(TAG, "当前页:"+page);
        //        https://gitee.com/api/v5/users/fandongtongxue_admin/starred?access_token=8b54574d49a35d59fcbff25c54d3e934&limit=20&sort=created&direction=desc
        RxHttp.get("api/v5/search/users")
                .add("page", page)
                .add("q", key)
                .add("perpage","20")
                .add("order","desc")
                .toObservableString()
                .observeOn(Schedulers.io())
                .flatMap(response -> {
                    try {
                        List<MeEntry> starList = new Gson().fromJson(response, new TypeToken<List<MeEntry>>(){}.getType());
                        return Observable.just(starList);
                    }catch (JsonSyntaxException e) {
                        try {
                            ErrorResponse errorResponse = new Gson().fromJson(response, ErrorResponse.class);
                            // 假设你在ErrorResponse对象中有statusCode属性
                            int statusCode = errorResponse.getStatusCode();
                            return Observable.error(new ApiException(errorResponse, statusCode));
                        } catch (JsonSyntaxException ex) {
                            // 如果解析再次失败，抛出原始异常
                            return Observable.error(e);
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if (page == 1) {
                        adapter.removeAtRange(new IntRange(0, adapter.getItemCount() - 1));
                        adapter.addAll(s);
                    } else {
                        adapter.addAll(s);
                    }
                    adapter.notifyDataSetChanged();
                    if (s.size() < 20) {
                        binding.refreshLayout.finishLoadMoreWithNoMoreData();
                    } else {
                        binding.refreshLayout.finishLoadMore(0);
                    }
                    binding.refreshLayout.finishRefresh(0);
                }, throwable -> {
                    Log.d(TAG, "请求失败:", throwable);
                    if (throwable instanceof HttpStatusCodeException) {
                        HttpStatusCodeException apiException = (HttpStatusCodeException) throwable;
                        Toast.makeText(getActivity(), apiException.getMessage(), Toast.LENGTH_SHORT).show();
                        int statusCode = apiException.getStatusCode();
                        if (statusCode == 401) {
                            MainActivity activity = (MainActivity) getActivity();
                            activity.logout();
                        }
                    } else {
                        Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    binding.refreshLayout.finishRefresh(false);
                    binding.refreshLayout.finishLoadMore(false);
                });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onKeyChanged(String key) {
        this.key = key;
        headerRefresh();
    }
}