package com.xiaobingkj.giteer.entry;

import java.io.Serializable;

public class EventEntryPayloadIssue implements Serializable {
	private String html;

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
}
