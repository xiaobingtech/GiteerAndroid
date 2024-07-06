package com.xiaobingkj.giteer.entry;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class StarEntryParent implements Serializable {
    private List<StarEntryParentAssignee> assignee;
    private Integer assignees_number;
    private StarEntryParentAssigner assigner;
    private String blobs_url;
    private String branches_url;
    private Boolean can_comment;
    private String collaborators_url;
    private String comments_url;
    private String commits_url;
    private String contributors_url;
    private String created_at;
    private String default_branch;
    private String description;
    private String enterprise;
    private Boolean fork;
    private Integer forks_count;
    private String forks_url;
    private String full_name;
    private Boolean gvp;
    private Boolean has_issues;
    private Boolean has_page;
    private Boolean has_wiki;
    private String homepage;
    private String hooks_url;
    private String html_url;
    private String human_name;
    private Integer id;
    private Boolean internal;
    private Boolean issue_comment;
    private String issue_comment_url;
    private String issue_template_source;
    private String issues_url;
    private String keys_url;
    private String labels_url;
    private String language;
    private String license;
    private List<String> members;
    private String milestones_url;
    private String name;
    private StarEntryParentNamespace namespace;
    private String notifications_url;
    private Integer open_issues_count;
    private Boolean outsourced;
    private StarEntryParentOwner owner;
    private String paas;
    private String parent;
    private String path;
    @SerializedName("private")
    private Boolean isPrivate;
    private List<String> programs;
    private String project_creator;
    private List<String> project_labels;
    @SerializedName("public")
    private Boolean isPublic;
    private Boolean pull_requests_enabled;
    private String pulls_url;
    private String pushed_at;
    private Boolean recommend;
    private String releases_url;
    private String ssh_url;
    private Integer stargazers_count;
    private String stargazers_url;
    private String status;
    private String tags_url;
    private List<StarEntryParentTesters> testers;
    private Integer testers_number;
    private String updated_at;
    private String url;
    private Integer watchers_count;

    public List<StarEntryParentAssignee> getAssignee() {
        return assignee;
    }

    public void setAssignee(List<StarEntryParentAssignee> assignee) {
        this.assignee = assignee;
    }

    public Integer getAssignees_number() {
        return assignees_number;
    }

    public void setAssignees_number(Integer assignees_number) {
        this.assignees_number = assignees_number;
    }

    public StarEntryParentAssigner getAssigner() {
        return assigner;
    }

    public void setAssigner(StarEntryParentAssigner assigner) {
        this.assigner = assigner;
    }

    public String getBlobs_url() {
        return blobs_url;
    }

    public void setBlobs_url(String blobs_url) {
        this.blobs_url = blobs_url;
    }

    public String getBranches_url() {
        return branches_url;
    }

    public void setBranches_url(String branches_url) {
        this.branches_url = branches_url;
    }

    public Boolean getCan_comment() {
        return can_comment;
    }

    public void setCan_comment(Boolean can_comment) {
        this.can_comment = can_comment;
    }

    public String getCollaborators_url() {
        return collaborators_url;
    }

    public void setCollaborators_url(String collaborators_url) {
        this.collaborators_url = collaborators_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getCommits_url() {
        return commits_url;
    }

    public void setCommits_url(String commits_url) {
        this.commits_url = commits_url;
    }

    public String getContributors_url() {
        return contributors_url;
    }

    public void setContributors_url(String contributors_url) {
        this.contributors_url = contributors_url;
    }

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

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public Boolean getFork() {
        return fork;
    }

    public void setFork(Boolean fork) {
        this.fork = fork;
    }

    public Integer getForks_count() {
        return forks_count;
    }

    public void setForks_count(Integer forks_count) {
        this.forks_count = forks_count;
    }

    public String getForks_url() {
        return forks_url;
    }

    public void setForks_url(String forks_url) {
        this.forks_url = forks_url;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Boolean getGvp() {
        return gvp;
    }

    public void setGvp(Boolean gvp) {
        this.gvp = gvp;
    }

    public Boolean getHas_issues() {
        return has_issues;
    }

    public void setHas_issues(Boolean has_issues) {
        this.has_issues = has_issues;
    }

    public Boolean getHas_page() {
        return has_page;
    }

    public void setHas_page(Boolean has_page) {
        this.has_page = has_page;
    }

    public Boolean getHas_wiki() {
        return has_wiki;
    }

    public void setHas_wiki(Boolean has_wiki) {
        this.has_wiki = has_wiki;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getHooks_url() {
        return hooks_url;
    }

    public void setHooks_url(String hooks_url) {
        this.hooks_url = hooks_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
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

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    public Boolean getIssue_comment() {
        return issue_comment;
    }

    public void setIssue_comment(Boolean issue_comment) {
        this.issue_comment = issue_comment;
    }

    public String getIssue_comment_url() {
        return issue_comment_url;
    }

    public void setIssue_comment_url(String issue_comment_url) {
        this.issue_comment_url = issue_comment_url;
    }

    public String getIssue_template_source() {
        return issue_template_source;
    }

    public void setIssue_template_source(String issue_template_source) {
        this.issue_template_source = issue_template_source;
    }

    public String getIssues_url() {
        return issues_url;
    }

    public void setIssues_url(String issues_url) {
        this.issues_url = issues_url;
    }

    public String getKeys_url() {
        return keys_url;
    }

    public void setKeys_url(String keys_url) {
        this.keys_url = keys_url;
    }

    public String getLabels_url() {
        return labels_url;
    }

    public void setLabels_url(String labels_url) {
        this.labels_url = labels_url;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public String getMilestones_url() {
        return milestones_url;
    }

    public void setMilestones_url(String milestones_url) {
        this.milestones_url = milestones_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StarEntryParentNamespace getNamespace() {
        return namespace;
    }

    public void setNamespace(StarEntryParentNamespace namespace) {
        this.namespace = namespace;
    }

    public String getNotifications_url() {
        return notifications_url;
    }

    public void setNotifications_url(String notifications_url) {
        this.notifications_url = notifications_url;
    }

    public Integer getOpen_issues_count() {
        return open_issues_count;
    }

    public void setOpen_issues_count(Integer open_issues_count) {
        this.open_issues_count = open_issues_count;
    }

    public Boolean getOutsourced() {
        return outsourced;
    }

    public void setOutsourced(Boolean outsourced) {
        this.outsourced = outsourced;
    }

    public StarEntryParentOwner getOwner() {
        return owner;
    }

    public void setOwner(StarEntryParentOwner owner) {
        this.owner = owner;
    }

    public String getPaas() {
        return paas;
    }

    public void setPaas(String paas) {
        this.paas = paas;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public List<String> getPrograms() {
        return programs;
    }

    public void setPrograms(List<String> programs) {
        this.programs = programs;
    }

    public String getProject_creator() {
        return project_creator;
    }

    public void setProject_creator(String project_creator) {
        this.project_creator = project_creator;
    }

    public List<String> getProject_labels() {
        return project_labels;
    }

    public void setProject_labels(List<String> project_labels) {
        this.project_labels = project_labels;
    }


    public Boolean getPull_requests_enabled() {
        return pull_requests_enabled;
    }

    public void setPull_requests_enabled(Boolean pull_requests_enabled) {
        this.pull_requests_enabled = pull_requests_enabled;
    }

    public String getPulls_url() {
        return pulls_url;
    }

    public void setPulls_url(String pulls_url) {
        this.pulls_url = pulls_url;
    }

    public String getPushed_at() {
        return pushed_at;
    }

    public void setPushed_at(String pushed_at) {
        this.pushed_at = pushed_at;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public String getReleases_url() {
        return releases_url;
    }

    public void setReleases_url(String releases_url) {
        this.releases_url = releases_url;
    }

    public String getSsh_url() {
        return ssh_url;
    }

    public void setSsh_url(String ssh_url) {
        this.ssh_url = ssh_url;
    }

    public Integer getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(Integer stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public String getStargazers_url() {
        return stargazers_url;
    }

    public void setStargazers_url(String stargazers_url) {
        this.stargazers_url = stargazers_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTags_url() {
        return tags_url;
    }

    public void setTags_url(String tags_url) {
        this.tags_url = tags_url;
    }

    public List<StarEntryParentTesters> getTesters() {
        return testers;
    }

    public void setTesters(List<StarEntryParentTesters> testers) {
        this.testers = testers;
    }

    public Integer getTesters_number() {
        return testers_number;
    }

    public void setTesters_number(Integer testers_number) {
        this.testers_number = testers_number;
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

    public Integer getWatchers_count() {
        return watchers_count;
    }

    public void setWatchers_count(Integer watchers_count) {
        this.watchers_count = watchers_count;
    }
}


