package com.xiaobingkj.giteer.ui.search;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.chad.library.adapter4.viewholder.QuickViewHolder;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.entry.StarEntry;
import com.xiaobingkj.giteer.entry.TrendSubEntry;

import cn.carbs.android.avatarimageview.library.AvatarImageView;

public class SearchRepoSubListAdapter extends BaseQuickAdapter<StarEntry, QuickViewHolder> {
    @Override
    protected void onBindViewHolder(@NonNull QuickViewHolder quickViewHolder, int i, @Nullable StarEntry starEntry) {
        assert starEntry != null;
        AvatarImageView avatar = quickViewHolder.getView(R.id.avatar);
        if (starEntry.getOwner().getAvatar_url().contains("no_portrait.png")) {
            avatar.setTextAndColor(starEntry.getOwner().getName().substring(0, 1), R.color.gray);
        }else{
            Glide.with(quickViewHolder.itemView).load(starEntry.getOwner().getAvatar_url()).into(avatar);
        }

        quickViewHolder.setText(R.id.name, starEntry.getHuman_name());
        quickViewHolder.setText(R.id.desc, starEntry.getDescription());
        quickViewHolder.setText(R.id.time, starEntry.getUpdated_at());
    }

    @NonNull
    @Override
    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
        return new QuickViewHolder(R.layout.item_star, viewGroup);
    }
}
