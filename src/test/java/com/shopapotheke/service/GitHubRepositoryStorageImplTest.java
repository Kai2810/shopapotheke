package com.shopapotheke.service;

import com.shopapotheke.client.GitHubSearchClient;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GitHubRepositoryStorageImplTest {


    private static GitHubRepositoryStorageImpl gitHubRepositoryStorage;
    private static GitHubSearchClient gitHubSearchClient;

    // Api Result is pretty much dynamic and keep changing all the time so cant check actual value

    @BeforeAll
    static void init() {
        gitHubRepositoryStorage = new GitHubRepositoryStorageImpl();
        gitHubSearchClient = new GitHubSearchClient(new RestTemplate());
        gitHubRepositoryStorage.clear();

        ReflectionTestUtils.setField(gitHubSearchClient, "gitHubUrl", "https://api.github.com/search/repositories");
    }

    @Test
    @Order(1)
    void testSaveRepository_ShouldAbleToSaveRepository() {
        var data = gitHubSearchClient.getAllRepository();
        assertDoesNotThrow(
                () -> gitHubRepositoryStorage.saveRepository(data)
        );
        assertEquals(data.size(), gitHubRepositoryStorage.findAllGitHubRepository().size());
    }

    @Test
    @Order(2)
    void testDeleteRepository_shouldAbleToDeleteRepository() {
        var data = gitHubSearchClient.getAllRepository();
        assertDoesNotThrow(
                () -> gitHubRepositoryStorage.deleteRepository(data.get(0).getName())
        );
        assertEquals(data.size()-1, gitHubRepositoryStorage.findAllGitHubRepository().size());
    }

    @Test
    @Order(3)
    void testFindAllGitHubRepository_ShouldAbleToFindAllGitHubRepository() {
        var result = gitHubRepositoryStorage.findAllGitHubRepository();
        assertNotNull(result);
    }

    @Test
    @Order(4)
    void testFindAllGitHubRepositoryByDateOnwards_shouldAbleToFindAllGitHubRepositoryByDateOnwards() {
        var result = gitHubRepositoryStorage.findAllGitHubRepositoryByDateOnwards(LocalDate.now().minusYears(5));
        assertNotNull(result);
    }

    @Test
    @Order(5)
    void testClear_shouldAbleToClearRepository() {
        var data = gitHubSearchClient.getAllRepository();
        assertDoesNotThrow(
                () -> gitHubRepositoryStorage.hashCode()
        );
        assertNotEquals(data.size(),gitHubRepositoryStorage.findAllGitHubRepository().size() );
    }
}