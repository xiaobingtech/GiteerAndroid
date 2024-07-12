package com.xiaobingkj.giteer.entry;

import java.io.Serializable;

public class TrendSubEntryOwner implements Serializable {
	private String created_at;
	private String email;
	private Integer id;
	private String name;
	private String new_portrait;
	private String portrait_url;
	private String state;
	private String username;

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNew_portrait() {
		return new_portrait;
	}

	public void setNew_portrait(String new_portrait) {
		this.new_portrait = new_portrait;
	}

	public String getPortrait_url() {
		return portrait_url;
	}

	public void setPortrait_url(String portrait_url) {
		this.portrait_url = portrait_url;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
