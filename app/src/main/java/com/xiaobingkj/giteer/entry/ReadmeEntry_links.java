package com.xiaobingkj.giteer.entry;

import java.io.Serializable;

public class ReadmeEntry_links implements Serializable {
    private String html;
    private String self;

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }
}
