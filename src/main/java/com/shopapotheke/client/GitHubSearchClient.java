package com.shopapotheke.client;

import com.shopapotheke.dto.GitRepositories;
import com.shopapotheke.dto.Repository;
import com.shopapotheke.exception.ClientApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.web.util.UriComponentsBuilder.fromUriString;

@Component
@Slf4j
public class GitHubSearchClient {

    private static final String GET_ALL_REPO = "?q=stars:>1&sort=stars";
    private static final String GET_BY_DATE = "?q=created:>{startDate}&sort=stars&order=desc";

    private final RestTemplate restTemplate;

    @Value("${shopapotheke.github.url}")
    private String gitHubUrl;

    public GitHubSearchClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Cacheable("getAllRepository")
    public List<Repository> getAllRepository() {
        try {
            UriComponents uri = fromUriString(gitHubUrl.concat(GET_ALL_REPO)).build();
            GitRepositories gitRepositories = restTemplate.exchange(uri.toUriString(), HttpMethod.GET, null, GitRepositories.class).getBody();
            return gitRepositories.getRepositories();
        } catch (Exception e) {
            //log.error(e.getMessage(), e);
            throw new ClientApiException(e);
        }
    }

    @Cacheable("getAllRepositoryByDateOnwards")
    public List<Repository> getAllRepositoryByDateOnwards(LocalDate dateOnwards) {
        try {
            UriComponents uri = fromUriString(gitHubUrl.concat(GET_BY_DATE)).buildAndExpand(dateOnwards.toString());
            GitRepositories clientRolesArray = restTemplate.exchange(uri.toUriString(), HttpMethod.GET, null, GitRepositories.class).getBody();
            return clientRolesArray.getRepositories();
        } catch (Exception e) {
            //log.error(e.getMessage(), e);
            throw new ClientApiException(e);
        }
    }


}
