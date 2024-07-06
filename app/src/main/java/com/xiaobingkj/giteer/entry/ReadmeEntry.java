package com.xiaobingkj.giteer.entry;

//
//  ReadmeEntry.java
//
//
//  Created by JSONConverter on 2024/02/29.
//  Copyright © 2024年 JSONConverter. All rights reserved.
//

import java.io.Serializable;

public class ReadmeEntry implements Serializable {
    private ReadmeEntry_links _links;
    private String content;
    private String download_url;
    private String encoding;
    private String html_url;
    private String name;
    private String path;
    private String sha;
    private Integer size;
    private String type;
    private String url;

    public ReadmeEntry_links get_links() {
        return _links;
    }

    public void set_links(ReadmeEntry_links _links) {
        this._links = _links;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDownload_url() {
        return download_url;
    }

    public void setDownload_url(String download_url) {
        this.download_url = download_url;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
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

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
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


