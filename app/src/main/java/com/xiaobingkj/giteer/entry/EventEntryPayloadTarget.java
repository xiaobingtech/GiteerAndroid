package com.xiaobingkj.giteer.entry;

import java.io.Serializable;

public class EventEntryPayloadTarget implements Serializable {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
