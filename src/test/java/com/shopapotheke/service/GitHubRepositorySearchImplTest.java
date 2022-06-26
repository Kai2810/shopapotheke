package com.shopapotheke.service;

import com.shopapotheke.client.GitHubSearchClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GitHubRepositorySearchImplTest {

    private static GitHubRepositorySearch gitHubRepositorySearch;
    private static GitHubRepositoryStorageImpl gitHubRepositoryStorage;
    private static GitHubSearchClient gitHubSearchClient;

    // Api Result is pretty much dynamic and keep changing all the time so cant check actual value
    @BeforeAll
    static void init() {
        gitHubRepositoryStorage = new GitHubRepositoryStorageImpl();
        gitHubSearchClient = new GitHubSearchClient(new RestTemplate());
        ReflectionTestUtils.setField(gitHubSearchClient, "gitHubUrl", "https://api.github.com/search/repositories");

        gitHubRepositorySearch = new GitHubRepositorySearchImpl(gitHubRepositoryStorage, gitHubSearchClient);
    }


    @Test
    void testGetMostPopularRepo() {
        var data = gitHubRepositoryStorage.findAllGitHubRepository();
        assertDoesNotThrow(
                () -> gitHubRepositorySearch.getMostPopularRepo(10)
        );
        assertNotEquals(data.size(),  gitHubRepositorySearch.getMostPopularRepo(10).size());
    }

    @Test
    void testGetMostPopularRepoByDate() {
        var data = gitHubRepositorySearch.getMostPopularRepoByDate(LocalDate.now());
        assertDoesNotThrow(
                () -> gitHubRepositorySearch.getMostPopularRepoByDate(LocalDate.now())
        );
        assertEquals(data.size(), gitHubRepositorySearch.getMostPopularRepoByDate(LocalDate.now()).size());
    }

}