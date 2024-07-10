package com.xiaobingkj.giteer.ui.event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter4.BaseQuickAdapter;
import com.chad.library.adapter4.viewholder.QuickViewHolder;
import com.xiaobingkj.giteer.R;
import com.xiaobingkj.giteer.entry.EventEntry;

import java.util.ArrayList;
import java.util.List;

public class EventListAdapter extends BaseQuickAdapter<EventEntry, QuickViewHolder> {


    @Override
    protected void onBindViewHolder(@NonNull QuickViewHolder quickViewHolder, int i, @Nullable EventEntry eventEntry) {
        ImageView avatar = quickViewHolder.getView(R.id.avatar);
        Glide.with(quickViewHolder.itemView).load(eventEntry.getActor().getAvatar_url()).into(avatar);
        if (eventEntry.getType() != null) {
            switch (eventEntry.getType()) {
                case "CommitCommentEvent":
                    if (eventEntry.getPayload() != null) {
                        quickViewHolder.setText(R.id.content, eventEntry.getActor().getName() + " 评论了" + eventEntry.getRepo().getHuman_name() + " " + eventEntry.getPayload().getComment().getBody());
                    }

                    break;
                case "PullRequestCommentEvent":
                    if (eventEntry.getPayload() != null) {
                        quickViewHolder.setText(R.id.content,eventEntry.getActor().getName() + " 评论了" + eventEntry.getRepo().getHuman_name() + " " + eventEntry.getPayload().getComment().getBody());
                    }

                    break;
                case "FollowEvent":
                    if (eventEntry.getPayload() != null) {
                        quickViewHolder.setText(R.id.content,eventEntry.getActor().getName() + (eventEntry.getPayload().getFollowed() ? "关注了" : "取消关注了") + eventEntry.getPayload().getTarget().getName());
                    }

                    break;
                case "ProjectCommentEvent":
                    if (eventEntry.getPayload() != null) {
                        quickViewHolder.setText(R.id.content,eventEntry.getActor().getName() + " 评论了" + eventEntry.getRepo().getHuman_name() + " " + eventEntry.getPayload().getComment().getBody());
                    }

                    break;
                case "PullRequestEvent":
                    if (eventEntry.getPayload() != null) {
                        quickViewHolder.setText(R.id.content,eventEntry.getActor().getName() + (eventEntry.getPayload().getAction() == "closed" ? "关闭了" : "打开了") + eventEntry.getPayload().getTitle());
                    }

                    break;
                case "IssueCommentEvent":
                    if (eventEntry.getPayload() != null) {
                        if (eventEntry.getPayload().getIssue().getHtml() != null) {
                            String[] htmls = eventEntry.getPayload().getIssue().getHtml().split("/");
                            String issue = htmls[htmls.length - 1];
                            quickViewHolder.setText(R.id.content,eventEntry.getActor().getName() + " 评论了Issue " + issue + "在" + eventEntry.getRepo().getHuman_name());
                        }
                    }

                    break;
                case "ForkEvent":
                    quickViewHolder.setText(R.id.content,eventEntry.getActor().getName() + " Fork了" + eventEntry.getRepo().getHuman_name());
                    break;
                case "MemberEvent":
                    quickViewHolder.setText(R.id.content,eventEntry.getActor().getName() + " 加入了" + eventEntry.getRepo().getHuman_name());
                    break;
                case "CreateEvent":
                    quickViewHolder.setText(R.id.content,eventEntry.getActor().getName() + " 创建了" + eventEntry.getRepo().getHuman_name());
                    break;
                case "StarEvent":
                    quickViewHolder.setText(R.id.content,eventEntry.getActor().getName() + " Star了" + eventEntry.getRepo().getHuman_name());
                    break;
                case "DeleteEvent":
                    quickViewHolder.setText(R.id.content,eventEntry.getActor().getName() + " 删除了" + eventEntry.getRepo().getHuman_name());
                    break;
                case "PushEvent":
                    String[] refs = eventEntry.getPayload().getRef().split("/");
                    String ref = refs[refs.length - 1];
                    quickViewHolder.setText(R.id.content,eventEntry.getActor().getName() + " 推送 " + ref + " 在 " + eventEntry.getRepo().getHuman_name());
                    break;
                case "IssueEvent":
                    String[] urls = eventEntry.getPayload().getUrl().split("/");
                    String iss = urls[urls.length - 1];
                    quickViewHolder.setText(R.id.content,eventEntry.getActor().getName() + " 打开了Issue " + iss + " 在 " + eventEntry.getRepo().getHuman_name());
                    break;
                default:
                    break;
            }
        }
        TextView commitIdTV = quickViewHolder.getView(R.id.commit_id);
        TextView commitTitleTV = quickViewHolder.getView(R.id.commit_title);
        if (eventEntry.getPayload() != null) {
            if (eventEntry.getPayload().getCommits() != null) {
                if (eventEntry.getPayload().getCommits().size() > 0) {
                    commitIdTV.setText(eventEntry.getPayload().getCommits().get(0).getSha().substring(0, 6));
                    commitTitleTV.setText(eventEntry.getPayload().getCommits().get(0).getMessage());
                }else{
                    commitIdTV.setVisibility(View.GONE);
                    commitTitleTV.setVisibility(View.GONE);
                }
            }else{
                commitIdTV.setVisibility(View.GONE);
                commitTitleTV.setVisibility(View.GONE);
            }
        }else{
            commitIdTV.setVisibility(View.GONE);
            commitTitleTV.setVisibility(View.GONE);
        }
        quickViewHolder.setText(R.id.time, eventEntry.getCreated_at());
    }

    @NonNull
    @Override
    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
        return new QuickViewHolder(R.layout.item_event, viewGroup);
    }

}
