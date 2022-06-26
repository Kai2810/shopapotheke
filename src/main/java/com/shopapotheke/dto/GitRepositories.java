package com.shopapotheke.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GitRepositories {

    @JsonProperty("items")
    private List<Repository> repositories;

    public GitRepositories() {
    }

    public GitRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public List<Repository> getRepositories() {
        return this.repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

}
