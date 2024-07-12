package com.xiaobingkj.giteer.entry;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TrendSubEntryNamespace implements Serializable {
	private String address;
	private String avatar;
	private String complete_name;
	private String complete_path;
	private String created_at;
	private String description;
	private String email;
	private Integer enterprise_id;
	private String from;
	private Integer id;
	private String initial_branch;
	private Integer level;
	private String location;
	private String name;
	private Boolean outsourced;
	private Integer owner_id;
	private Integer parent_id;
	private String path;
	@SerializedName("public")
	private Boolean isPublic;
	private String updated_at;
	private String url;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getComplete_name() {
		return complete_name;
	}

	public void setComplete_name(String complete_name) {
		this.complete_name = complete_name;
	}

	public String getComplete_path() {
		return complete_path;
	}

	public void setComplete_path(String complete_path) {
		this.complete_path = complete_path;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(Integer enterprise_id) {
		this.enterprise_id = enterprise_id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInitial_branch() {
		return initial_branch;
	}

	public void setInitial_branch(String initial_branch) {
		this.initial_branch = initial_branch;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOutsourced() {
		return outsourced;
	}

	public void setOutsourced(Boolean outsourced) {
		this.outsourced = outsourced;
	}

	public Integer getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(Integer owner_id) {
		this.owner_id = owner_id;
	}

	public Integer getParent_id() {
		return parent_id;
	}

	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean getPublic() {
		return isPublic;
	}

	public void setPublic(Boolean aPublic) {
		isPublic = aPublic;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
