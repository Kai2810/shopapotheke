package com.shopapotheke.service;


import com.shopapotheke.dto.Repository;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Collections.sort;

@org.springframework.stereotype.Repository
@Slf4j
public class GitHubRepositoryStorageImpl implements GitHubRepositoryStorage {

    private static final Map<String, Repository> githubRepository = new ConcurrentHashMap<>();


    @Override
    public void saveRepository(List<Repository> gitRepositories) {
        gitRepositories.stream()
                .filter(it -> !githubRepository.containsKey(it.getName()))
                .forEach(it -> githubRepository.put(it.getName(), it));

    }

    @Override
    public void deleteRepository(String repoName) {
        githubRepository.remove(repoName);
    }

    @Override
    public List<Repository> findAllGitHubRepository() {
        var repositories = new ArrayList<>(githubRepository.values());
        sort(repositories, Comparator.comparingLong(Repository::getStargazers_count).reversed());
        return repositories;
    }

    @Override
    public List<Repository> findAllGitHubRepositoryByDateOnwards(LocalDate startDate) {
        return new ArrayList<>(githubRepository.values());
    }

    @Override
    public void clear() {
        githubRepository.clear();
    }
}
