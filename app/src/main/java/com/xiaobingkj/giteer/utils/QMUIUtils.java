package com.xiaobingkj.giteer.utils;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.xiaobingkj.giteer.R;

public class QMUIUtils {
    //toolbar
    public static void showToolBar(QMUITopBar qmuiTopBar, QmBackOnclickListener listener, Activity activity, String name) {
        if (qmuiTopBar != null) {
            qmuiTopBar.setTitle(name).setTextSize(CommonUtils.dip2px(activity, 18));
            View backView = View.inflate(activity, R.layout.topbar_left, null);
            backView.findViewById(R.id.tv_back).setOnClickListener(v -> {
                listener.onBackClick();
            });
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            qmuiTopBar.addLeftView(backView, 1, layoutParams);
        }

    }

    public interface QmBackOnclickListener {
        void onBackClick();
    }
}
