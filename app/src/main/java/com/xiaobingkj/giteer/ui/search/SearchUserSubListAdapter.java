package com.xiaobingkj.giteer.ui.search;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.chad.library.adapter4.viewholder.QuickViewHolder;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.entry.MeEntry;
import com.xiaobingkj.giteer.entry.StarEntry;

import cn.carbs.android.avatarimageview.library.AvatarImageView;

public class SearchUserSubListAdapter extends BaseQuickAdapter<MeEntry, QuickViewHolder> {
    @Override
    protected void onBindViewHolder(@NonNull QuickViewHolder quickViewHolder, int i, @Nullable MeEntry meEntry) {
        assert meEntry != null;
        AvatarImageView avatar = quickViewHolder.getView(R.id.avatar);
        if (meEntry.getAvatar_url().contains("no_portrait.png")) {
            avatar.setTextAndColor(meEntry.getName().substring(0, 1), R.color.gray);
        }else{
            Glide.with(quickViewHolder.itemView).load(meEntry.getAvatar_url()).into(avatar);
        }

        quickViewHolder.setText(R.id.name, meEntry.getName());
    }

    @NonNull
    @Override
    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
        return new QuickViewHolder(R.layout.item_user, viewGroup);
    }
}
