package com.xiaobingkj.giteer.entry;//
//  RepoTreeEntry.java
//
//
//  Created by JSONConverter on 2024/07/16.
//  Copyright © 2024年 JSONConverter. All rights reserved.
//

import java.io.Serializable;
import java.util.List;

public class RepoTreeEntry implements Serializable {
	private RepoTreeEntry_links _links;
	private String download_url;
	private String html_url;
	private String name;
	private String path;
	private String sha;
	private String size;
	private String type;
	private String url;

	public RepoTreeEntry_links get_links() {
		return _links;
	}

	public void set_links(RepoTreeEntry_links _links) {
		this._links = _links;
	}

	public String getDownload_url() {
		return download_url;
	}

	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSha() {
		return sha;
	}

	public void setSha(String sha) {
		this.sha = sha;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

