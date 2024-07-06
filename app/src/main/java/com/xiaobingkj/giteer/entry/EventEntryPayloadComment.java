package com.xiaobingkj.giteer.entry;

import java.io.Serializable;

public class EventEntryPayloadComment implements Serializable {
	private String body;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
