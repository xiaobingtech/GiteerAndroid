package com.xiaobingkj.giteer.entry;

import java.io.Serializable;

public class EventEntryPayloadCommits implements Serializable {
	private EventEntryPayloadCommitsAuthor author;
	private String message;
	private String sha;
	private String url;

	public EventEntryPayloadCommitsAuthor getAuthor() {
		return author;
	}

	public void setAuthor(EventEntryPayloadCommitsAuthor author) {
		this.author = author;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
