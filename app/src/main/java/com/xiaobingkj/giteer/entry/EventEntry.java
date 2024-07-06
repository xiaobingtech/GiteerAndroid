package com.xiaobingkj.giteer.entry;//
//  EventEntry.java
//
//
//  Created by JSONConverter on 2024/07/01.
//  Copyright © 2024年 JSONConverter. All rights reserved.
//

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class EventEntry implements Serializable {
	private EventEntryActor actor;
	private String created_at;
	private Integer id;
	private EventEntryOrg org;
	private EventEntryPayload payload;
	@SerializedName("public")
	private Boolean isPublic;
	private EventEntryRepo repo;
	private String type;

	public EventEntryActor getActor() {
		return actor;
	}

	public void setActor(EventEntryActor actor) {
		this.actor = actor;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EventEntryOrg getOrg() {
		return org;
	}

	public void setOrg(EventEntryOrg org) {
		this.org = org;
	}

	public EventEntryPayload getPayload() {
		return payload;
	}

	public void setPayload(EventEntryPayload payload) {
		this.payload = payload;
	}

	public Boolean getPublic() {
		return isPublic;
	}

	public void setPublic(Boolean aPublic) {
		isPublic = aPublic;
	}

	public EventEntryRepo getRepo() {
		return repo;
	}

	public void setRepo(EventEntryRepo repo) {
		this.repo = repo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

