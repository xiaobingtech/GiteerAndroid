package com.xiaobingkj.giteer.entry;

import java.io.Serializable;

public class StarEntryPrograms implements Serializable {
    private StarEntryProgramsAssignee assignee;
    private StarEntryProgramsAuthor author;
    private String description;
    private Integer id;
    private String name;

    public StarEntryProgramsAssignee getAssignee() {
        return assignee;
    }

    public void setAssignee(StarEntryProgramsAssignee assignee) {
        this.assignee = assignee;
    }

    public StarEntryProgramsAuthor getAuthor() {
        return author;
    }

    public void setAuthor(StarEntryProgramsAuthor author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}

