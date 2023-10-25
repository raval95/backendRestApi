package com.recruitment.rest.model;


import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("login")
    private String login;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("followers")
    private Integer liczbaFollowers;
    @JsonProperty("public_repos")
    private Integer liczbaPublicRepos;
    @JsonProperty("created_at")
    private String createdAt;


    public User() {
    }

    public User(Long id, String login, String name, String type, String avatarUrl, Integer liczbaFollowers, Integer liczbaPublicRepos, String createdAt) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.type = type;
        this.avatarUrl = avatarUrl;
        this.liczbaFollowers = liczbaFollowers;
        this.liczbaPublicRepos = liczbaPublicRepos;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getLiczbaFollowers() {
        return liczbaFollowers;
    }

    public void setLiczbaFollowers(Integer liczbaFollowers) {
        this.liczbaFollowers = liczbaFollowers;
    }

    public Integer getLiczbaPublicRepos() {
        return liczbaPublicRepos;
    }

    public void setLiczbaPublicRepos(Integer liczbaPublicRepos) {
        this.liczbaPublicRepos = liczbaPublicRepos;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}