package com.shopapotheke.controller;

import com.shopapotheke.dto.Repository;
import com.shopapotheke.service.GitHubRepositorySearch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
@Api("ShopApotheke Popular Repositories Controller")
public class PopularRepositoriesController {

    private final GitHubRepositorySearch gitHubRepositorySearch;

    @Autowired
    public PopularRepositoriesController(GitHubRepositorySearch gitHubRepositorySearch) {
        this.gitHubRepositorySearch = gitHubRepositorySearch;
    }

    @GetMapping("/mostpopular/repository")
    @ApiOperation("Retrieves list of the most popular repositories")
    public List<Repository> getRepositoryList(@RequestParam(defaultValue = "100000") int limit) {
        return gitHubRepositorySearch.getMostPopularRepo(limit);
    }

    @GetMapping("/mostpopular/repository/search")
    @ApiOperation("Retrieves list of the most popular repositories using filer")
    public List<Repository> getRepositoryBySearch(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOnwards) {
        return gitHubRepositorySearch.getMostPopularRepoByDate(dateOnwards);
    }


}
