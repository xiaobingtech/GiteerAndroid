package com.xiaobingkj.giteer.ui.me;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter4.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.xiaobingkj.giteer.MainActivity;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.listener.TokenChangeListener;
import com.xiaobingkj.giteer.databinding.FragmentMeBinding;
import com.xiaobingkj.giteer.entry.ApiException;
import com.xiaobingkj.giteer.entry.ErrorResponse;
import com.xiaobingkj.giteer.entry.MeEntry;
import com.xiaobingkj.giteer.singleton.Giteer;

import java.util.ArrayList;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import kotlin.ranges.IntRange;
import rxhttp.RxHttp;
import rxhttp.wrapper.exception.HttpStatusCodeException;


public class MeFragment extends Fragment implements TokenChangeListener {
    public static final String TAG = "MeFragment";

    private FragmentMeBinding binding;
    private MeAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_me, container, false);
        View root = binding.getRoot();

        binding.recycleView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        Drawable dividerDrawable = ContextCompat.getDrawable(getActivity(), R.drawable.me_divider);
        if (dividerDrawable != null) {
            dividerItemDecoration.setDrawable(dividerDrawable);
        }
        binding.recycleView.addItemDecoration(dividerItemDecoration);

        adapter = new MeAdapter(new ArrayList<>());
        binding.recycleView.setAdapter(adapter);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener<MeEntry>() {
            @Override
            public void onClick(@NonNull BaseQuickAdapter<MeEntry, ?> baseQuickAdapter, @NonNull View view, int i) {
                MainActivity activity = (MainActivity) getActivity();
                activity.logout();
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        requestData();
    }

    private void requestData() {
        RxHttp.get("api/v5/users/" + Giteer.getInstance().getUser().getLogin())
                .toObservableString()
                .observeOn(Schedulers.io())
                .flatMap(response -> {
                    try {
                        MeEntry starList = new Gson().fromJson(response, MeEntry.class);
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
                    if (adapter.getItemCount() == 5) {
                        adapter.removeAtRange(new IntRange(0, 4));
                    }

                    adapter.add(s);
                    adapter.add(s);
                    adapter.add(s);
                    adapter.add(s);
                    adapter.add(s);
                    adapter.notifyDataSetChanged();
                }, throwable -> {
                    Log.d(TAG, "请求失败:", throwable);
                    if (throwable instanceof HttpStatusCodeException) {
                        HttpStatusCodeException apiException = (HttpStatusCodeException) throwable;
                        Toast.makeText(getActivity(), apiException.getMessage(), Toast.LENGTH_SHORT).show();
                        int statusCode = apiException.getStatusCode();
                        if (statusCode == 401) {

                        }
                    } else {
                        Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onTokenChanged() {
        requestData();
    }
}