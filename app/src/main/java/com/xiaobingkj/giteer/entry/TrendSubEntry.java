package com.xiaobingkj.giteer.entry;//
//  TrendSubEntry.java
//
//
//  Created by JSONConverter on 2024/07/12.
//  Copyright © 2024年 JSONConverter. All rights reserved.
//

import com.google.gson.annotations.SerializedName;
import com.xiaobingkj.giteer.entry.TrendSubEntryNamespace;
import com.xiaobingkj.giteer.entry.TrendSubEntryOwner;

import java.io.Serializable;
import java.util.List;

public class TrendSubEntry implements Serializable {
	private String created_at;
	private String default_branch;
	private String description;
	@SerializedName("fork?")
	private Boolean isFork;
	private Integer forks_count;
	private Integer id;
	private Boolean is_pull_request_readonly;
	private Boolean issues_enabled;
	private String language;
	private String last_push_at;
	private String name;
	private String name_with_namespace;
	private TrendSubEntryNamespace namespace;
	private TrendSubEntryOwner owner;
	private String paas;
	private Integer parent_id;
	private String parent_path_with_namespace;
	private String path;
	private String path_with_namespace;
	private Boolean project_is_started;
	@SerializedName("public")
	private Boolean isPublic;
	private Boolean pull_requests_enabled;
	private Integer recomm;
	private String relation;
	private String stared;
	private Integer stars_count;
	private String watched;
	private Integer watches_count;
	private Boolean wiki_enabled;

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getDefault_branch() {
		return default_branch;
	}

	public void setDefault_branch(String default_branch) {
		this.default_branch = default_branch;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Integer getForks_count() {
		return forks_count;
	}

	public void setForks_count(Integer forks_count) {
		this.forks_count = forks_count;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIs_pull_request_readonly() {
		return is_pull_request_readonly;
	}

	public void setIs_pull_request_readonly(Boolean is_pull_request_readonly) {
		this.is_pull_request_readonly = is_pull_request_readonly;
	}

	public Boolean getIssues_enabled() {
		return issues_enabled;
	}

	public void setIssues_enabled(Boolean issues_enabled) {
		this.issues_enabled = issues_enabled;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getLast_push_at() {
		return last_push_at;
	}

	public void setLast_push_at(String last_push_at) {
		this.last_push_at = last_push_at;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_with_namespace() {
		return name_with_namespace;
	}

	public void setName_with_namespace(String name_with_namespace) {
		this.name_with_namespace = name_with_namespace;
	}

	public TrendSubEntryNamespace getNamespace() {
		return namespace;
	}

	public void setNamespace(TrendSubEntryNamespace namespace) {
		this.namespace = namespace;
	}

	public TrendSubEntryOwner getOwner() {
		return owner;
	}

	public void setOwner(TrendSubEntryOwner owner) {
		this.owner = owner;
	}

	public String getPaas() {
		return paas;
	}

	public void setPaas(String paas) {
		this.paas = paas;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public String getParent_path_with_namespace() {
		return parent_path_with_namespace;
	}

	public void setParent_path_with_namespace(String parent_path_with_namespace) {
		this.parent_path_with_namespace = parent_path_with_namespace;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath_with_namespace() {
		return path_with_namespace;
	}

	public void setPath_with_namespace(String path_with_namespace) {
		this.path_with_namespace = path_with_namespace;
	}

	public Boolean getProject_is_started() {
		return project_is_started;
	}

	public void setProject_is_started(Boolean project_is_started) {
		this.project_is_started = project_is_started;
	}

	public Boolean getFork() {
		return isFork;
	}

	public void setFork(Boolean fork) {
		isFork = fork;
	}

	public Boolean getPublic() {
		return isPublic;
	}

	public void setPublic(Boolean aPublic) {
		isPublic = aPublic;
	}

	public Boolean getPull_requests_enabled() {
		return pull_requests_enabled;
	}

	public void setPull_requests_enabled(Boolean pull_requests_enabled) {
		this.pull_requests_enabled = pull_requests_enabled;
	}

	public Integer getRecomm() {
		return recomm;
	}

	public void setRecomm(Integer recomm) {
		this.recomm = recomm;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getStared() {
		return stared;
	}

	public void setStared(String stared) {
		this.stared = stared;
	}

	public Integer getStars_count() {
		return stars_count;
	}

	public void setStars_count(Integer stars_count) {
		this.stars_count = stars_count;
	}

	public String getWatched() {
		return watched;
	}

	public void setWatched(String watched) {
		this.watched = watched;
	}

	public Integer getWatches_count() {
		return watches_count;
	}

	public void setWatches_count(Integer watches_count) {
		this.watches_count = watches_count;
	}

	public Boolean getWiki_enabled() {
		return wiki_enabled;
	}

	public void setWiki_enabled(Boolean wiki_enabled) {
		this.wiki_enabled = wiki_enabled;
	}
}

