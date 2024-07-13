package com.xiaobingkj.giteer.ui.trend;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.chad.library.adapter4.viewholder.QuickViewHolder;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.entry.TrendSubEntry;

import cn.carbs.android.avatarimageview.library.AvatarImageView;

public class TrendSubListAdapter extends BaseQuickAdapter<TrendSubEntry, QuickViewHolder> {
    @Override
    protected void onBindViewHolder(@NonNull QuickViewHolder quickViewHolder, int i, @Nullable TrendSubEntry trendSubEntry) {
        assert trendSubEntry != null;
        AvatarImageView avatar = quickViewHolder.getView(R.id.avatar);
        if (trendSubEntry.getOwner().getPortrait_url().contains("no_portrait.png")) {
            avatar.setTextAndColor(trendSubEntry.getOwner().getName().substring(0, 1), R.color.gray);
        }else{
            Glide.with(quickViewHolder.itemView).load(trendSubEntry.getOwner().getPortrait_url()).into(avatar);
        }

        quickViewHolder.setText(R.id.name, trendSubEntry.getName_with_namespace());
        quickViewHolder.setText(R.id.desc, trendSubEntry.getDescription());
        quickViewHolder.setText(R.id.time, trendSubEntry.getLast_push_at());
    }

    @NonNull
    @Override
    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
        return new QuickViewHolder(R.layout.item_star, viewGroup);
    }
}
