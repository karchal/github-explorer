package com.example.githubexplorer.client;

import com.example.githubexplorer.model.RepositoryInfo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class GitHubClient {

    @Value("${github.api.base-url}")
    private String baseUrl;

    private final CloseableHttpClient httpClient;
    private final Gson gson;

    public GitHubClient(CloseableHttpClient httpClient, Gson gson) {
        this.httpClient = httpClient;
        this.gson = gson;
    }

    public List<RepositoryInfo> getRepoList(String username) {

        try (CloseableHttpResponse response = httpClient.execute(new HttpGet(baseUrl + username + "/repos"))) {

            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode != 200) {
                //throw new UserNotFoundException("Failed to fetch data from GitHub API. Status code: " + statusCode);
                return new ArrayList<>(); //todo: remove this when an exception is thrown
            }

            String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
            log.info(responseString);
            return gson.fromJson(responseString, new TypeToken<List<RepositoryInfo>>(){}.getType());

        } catch (IOException e) {
            //throw new GitHubApiException("Failed to fetch data from GitHub API", e);
            log.error("Failed to fetch data from GitHub API");
        } catch (JsonSyntaxException e) {
            //throw new GitHubApiException("Failed to parse JSON response from GitHub API", e);
            log.error("Failed to parse JSON response from GitHub API");
        }
        return new ArrayList<>(); //todo: remove this when exceptions are thrown
    }
}

