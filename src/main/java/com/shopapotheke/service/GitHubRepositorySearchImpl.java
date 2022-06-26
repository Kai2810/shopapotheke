package com.shopapotheke.service;

import com.shopapotheke.client.GitHubSearchClient;
import com.shopapotheke.dto.Repository;
import com.shopapotheke.validators.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.sort;

@Service
@Slf4j
public class GitHubRepositorySearchImpl implements GitHubRepositorySearch {

    private final GitHubRepositoryStorage gitHubRepositoryStorage;
    private final GitHubSearchClient gitHubSearchClient;

    public GitHubRepositorySearchImpl(GitHubRepositoryStorage gitHubRepositoryStorage, GitHubSearchClient gitHubSearchClient) {
        this.gitHubRepositoryStorage = gitHubRepositoryStorage;
        this.gitHubSearchClient = gitHubSearchClient;
    }

    @Override
    public List<Repository> getMostPopularRepo(int limit) {
        var allGitRepositories = gitHubRepositoryStorage.findAllGitHubRepository();
        if (allGitRepositories.isEmpty()) {
            allGitRepositories = gitHubSearchClient.getAllRepository();
            addGitRepositories(allGitRepositories);
        }
        return allGitRepositories.stream().limit(limit).collect(Collectors.toList());
    }

    @Override
    public List<Repository> getMostPopularRepoByDate(LocalDate startDate) {
        var allGitRepositories = gitHubRepositoryStorage.findAllGitHubRepositoryByDateOnwards(startDate);
        if (allGitRepositories.isEmpty()) {
            allGitRepositories = gitHubSearchClient.getAllRepositoryByDateOnwards(startDate);
            addGitRepositories(allGitRepositories);
        }
        allGitRepositories.removeIf(it -> Validator.isRepositoryCreatedAfterDate(it, startDate));
        sort(allGitRepositories, Comparator.comparingLong(Repository::getStargazers_count).reversed());
        return allGitRepositories;
    }


    @Async
    public void addGitRepositories(List<Repository> gitRepositories) {
        gitHubRepositoryStorage.saveRepository(gitRepositories);
    }

    @Scheduled(fixedRate = 600000)
    public void fetchNewRepository() {
        var allGitRepositories = gitHubSearchClient.getAllRepository();
        addGitRepositories(allGitRepositories);
    }
}
