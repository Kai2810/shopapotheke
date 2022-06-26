package com.shopapotheke.service;

import com.shopapotheke.dto.Repository;

import java.time.LocalDate;
import java.util.List;

public interface GitHubRepositoryStorage {

    void saveRepository(List<Repository> gitRepositories);

    void deleteRepository(String repoName);

    List<Repository> findAllGitHubRepository();

    List<Repository> findAllGitHubRepositoryByDateOnwards(LocalDate startDate);

    void clear();


}
