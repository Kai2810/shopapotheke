package com.shopapotheke.service;

import com.shopapotheke.dto.Repository;

import java.time.LocalDate;
import java.util.List;

public interface GitHubRepositorySearch {
    List<Repository> getMostPopularRepo(int limit);

    List<Repository> getMostPopularRepoByDate(LocalDate startDate);
}
