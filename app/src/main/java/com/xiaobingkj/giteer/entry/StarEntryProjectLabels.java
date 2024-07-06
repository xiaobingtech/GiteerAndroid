package com.xiaobingkj.giteer.entry;

import java.io.Serializable;

public class StarEntryProjectLabels implements Serializable {
    private int id;
    private String name;
    private String ident;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }
}
