package com.xiaobingkj.giteer.ui.me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter4.BaseMultiItemAdapter;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.chad.library.adapter4.viewholder.QuickViewHolder;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.databinding.ItemMeListBinding;
import com.xiaobingkj.giteer.databinding.ItemMeTopBinding;
import com.xiaobingkj.giteer.entry.MeEntry;

import org.w3c.dom.Text;

import java.util.List;

import cn.carbs.android.avatarimageview.library.AvatarImageView;

public class MeAdapter extends BaseMultiItemAdapter<MeEntry> {

    public static class MeTopVH extends RecyclerView.ViewHolder {
        private ItemMeTopBinding viewBinding;

        private AvatarImageView avatar;
        private TextView nameTV;
        private TextView descTV;
        private TextView timeTV;

        public MeTopVH(ItemMeTopBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
            avatar = viewBinding.avatar;
            nameTV = viewBinding.name;
            descTV = viewBinding.desc;
            timeTV = viewBinding.time;
        }
    }

    public static class MeListVH extends RecyclerView.ViewHolder {
        private ItemMeListBinding viewBinding;
        private TextView titleTV;
        public MeListVH(ItemMeListBinding viewBinding) {
            super(viewBinding.getRoot());
            this.viewBinding = viewBinding;
            titleTV = viewBinding.title;
        }
    }

    public MeAdapter(List<MeEntry> data) {
        super(data);
        addItemType(ME_TOP_TYPE, new OnMultiItemAdapterListener<MeEntry, MeTopVH>() {
            @NonNull
            @Override
            public MeTopVH onCreate(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
                return new MeTopVH(ItemMeTopBinding.inflate(LayoutInflater.from(context), viewGroup, false));
            }

            @Override
            public void onBind(@NonNull MeTopVH viewHolder, int i, @Nullable MeEntry meEntry) {
                viewHolder.nameTV.setText(meEntry.getName() + "("  + meEntry.getLogin()+ ")");
                viewHolder.descTV.setText(meEntry.getBio());
                viewHolder.timeTV.setText("加入于"+meEntry.getCreated_at());
                AvatarImageView avatar = viewHolder.avatar;
                if (meEntry.getAvatar_url().equals("https://gitee.com/assets/no_portrait.png")) {
                    avatar.setTextAndColor(meEntry.getName().substring(0, 1), R.color.gray);
                }else{
                    Glide.with(viewHolder.itemView).load(meEntry.getAvatar_url()).into(avatar);
                }
            }
        }).addItemType(ME_LIST_TYPE, new OnMultiItemAdapterListener<MeEntry, MeListVH>() {
            @NonNull
            @Override
            public MeListVH onCreate(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
                return new MeListVH(ItemMeListBinding.inflate(LayoutInflater.from(context), viewGroup, false));
            }

            @Override
            public void onBind(@NonNull MeListVH viewHolder, int i, @Nullable MeEntry meEntry) {
                switch (i) {
                    case 1:
                        viewHolder.titleTV.setText("消息通知");
                        break;
                    case 2:
                        viewHolder.titleTV.setText(meEntry.getEmail());
                        break;
                    case 3:
                        viewHolder.titleTV.setText("公钥");
                        break;
                    case 4:
                        viewHolder.titleTV.setText("设置");
                        break;
                }

            }
        }).onItemViewType(new OnItemViewTypeListener<MeEntry>() {
            @Override
            public int onItemViewType(int i, @NonNull List<? extends MeEntry> list) {
                if (i == 0) {
                    return ME_TOP_TYPE;
                }
                return ME_LIST_TYPE;
            }
        });
    }

    private static final int ME_TOP_TYPE = 0;
    private static final int ME_LIST_TYPE = 1;
}
