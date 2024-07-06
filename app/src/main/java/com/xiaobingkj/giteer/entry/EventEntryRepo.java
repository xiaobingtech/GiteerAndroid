package com.xiaobingkj.giteer.entry;

import java.io.Serializable;

public class EventEntryRepo implements Serializable {
	private String full_name;
	private String human_name;
	private Integer id;
	private EventEntryRepoNamespace namespace;
	private String url;

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getHuman_name() {
		return human_name;
	}

	public void setHuman_name(String human_name) {
		this.human_name = human_name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EventEntryRepoNamespace getNamespace() {
		return namespace;
	}

	public void setNamespace(EventEntryRepoNamespace namespace) {
		this.namespace = namespace;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
