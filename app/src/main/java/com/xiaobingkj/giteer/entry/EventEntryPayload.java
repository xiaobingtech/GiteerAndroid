package com.xiaobingkj.giteer.entry;

import java.io.Serializable;
import java.util.List;

public class EventEntryPayload implements Serializable {
	private String after;
	private String before;
	private List<EventEntryPayloadCommits> commits;
	private String compare;
	private Boolean created;
	private Boolean deleted;
	private Boolean followed;
	private String action;
	private String title;
	private String url;
	private String ref;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private EventEntryPayloadIssue issue;

	public EventEntryPayloadIssue getIssue() {
		return issue;
	}

	public void setIssue(EventEntryPayloadIssue issue) {
		this.issue = issue;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	private EventEntryPayloadTarget target;

	public EventEntryPayloadTarget getTarget() {
		return target;
	}

	public void setTarget(EventEntryPayloadTarget target) {
		this.target = target;
	}

	public Boolean getFollowed() {
		return followed;
	}

	public void setFollowed(Boolean followed) {
		this.followed = followed;
	}

	private Integer size;
	private EventEntryPayloadComment comment;

	public String getAfter() {
		return after;
	}

	public void setAfter(String after) {
		this.after = after;
	}

	public String getBefore() {
		return before;
	}

	public void setBefore(String before) {
		this.before = before;
	}

	public List<EventEntryPayloadCommits> getCommits() {
		return commits;
	}

	public void setCommits(List<EventEntryPayloadCommits> commits) {
		this.commits = commits;
	}

	public String getCompare() {
		return compare;
	}

	public void setCompare(String compare) {
		this.compare = compare;
	}

	public Boolean getCreated() {
		return created;
	}

	public void setCreated(Boolean created) {
		this.created = created;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public EventEntryPayloadComment getComment() {
		return comment;
	}

	public void setComment(EventEntryPayloadComment comment) {
		this.comment = comment;
	}
}
