package com.shopapotheke.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class Repository {

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;
    @JsonProperty("language")
    private String language;
    @JsonProperty("stargazers_count")
    private Long stargazers_count;
    @JsonProperty("created_at")
    private Instant created_at;

    public Repository() {
    }

    public Repository(String name, String url, String language, Long stargazers_count, Instant created_at) {
        this.name = name;
        this.url = url;
        this.language = language;
        this.stargazers_count = stargazers_count;
        this.created_at = created_at;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public String getLanguage() {
        return this.language;
    }

    public Long getStargazers_count() {
        return this.stargazers_count;
    }

    public Instant getCreated_at() {
        return this.created_at;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setStargazers_count(Long stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public void setCreated_at(Instant created_at) {
        this.created_at = created_at;
    }
}
