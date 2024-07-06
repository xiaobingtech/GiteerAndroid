package com.xiaobingkj.giteer.ui.event;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.TokenChangeListener;
import com.xiaobingkj.giteer.databinding.FragmentEventBinding;
import com.xiaobingkj.giteer.entry.ApiException;
import com.xiaobingkj.giteer.entry.ErrorResponse;
import com.xiaobingkj.giteer.entry.EventEntry;
import com.xiaobingkj.giteer.singleton.Giteer;
import com.xiaobingkj.giteer.ui.login.LoginActivity;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.ranges.IntRange;
import rxhttp.RxHttp;
import rxhttp.wrapper.exception.HttpStatusCodeException;

public class EventFragment extends Fragment implements TokenChangeListener {

    public static final String TAG = "EventFragment";

    private FragmentEventBinding binding;
    private EventListAdapter adapter;
    private int prev_id;
    private List<EventEntry> models;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_event, container, false);
        View root = binding.getRoot();

        binding.listView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new EventListAdapter();
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

        headerRefresh();

        return root;
    }

    private void headerRefresh() {
        prev_id = 0;
        requestData();
    }

    private void footerRefresh() {
        requestData();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Resume");
    }

    public void requestData() {
        //        https://gitee.com/api/v5/users/fandongtongxue_admin/received_events?access_token=45face680d313b0749afb5a1891c245f&limit=100
        RxHttp.get("api/v5/users/"+ Giteer.getInstance().getUser().getLogin() + "/received_events")
                .add("limit", 100)
                .add("prev_id", prev_id)
                .toObservableString()
                .observeOn(Schedulers.io())
                .flatMap(response -> {
                    try {
                        List<EventEntry> starList = new Gson().fromJson(response, new TypeToken<List<EventEntry>>(){}.getType());
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
                .subscribe( s-> {
                    if (prev_id == 0) {
                        adapter.removeAtRange(new IntRange(0, adapter.getItemCount() - 1));
                        adapter.addAll(s);
                    } else {
                        adapter.addAll(s);
                    }
                    adapter.notifyDataSetChanged();
                    prev_id = s.get(s.size()-1).getId();
                    if (s.size() < 100) {
                        binding.refreshLayout.finishLoadMoreWithNoMoreData();
                    }else{
                        binding.refreshLayout.finishLoadMore(0);
                    }
                    binding.refreshLayout.finishRefresh(2000);
                }, throwable -> {
                    Log.d(TAG, "请求失败:", throwable);
                    if (throwable instanceof HttpStatusCodeException) {
                        HttpStatusCodeException apiException = (HttpStatusCodeException) throwable;
                        Toast.makeText(getActivity(), apiException.getMessage(), Toast.LENGTH_SHORT).show();
                        int statusCode = apiException.getStatusCode();
                        if (statusCode == 401) {

                        }
                    }else{
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
    public void onTokenChanged() {
        headerRefresh();
    }
}