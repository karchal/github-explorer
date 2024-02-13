package com.example.githubexplorer.service;

import com.example.githubexplorer.model.RepositoryInfo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class GitHubServiceImpl implements GitHubService {

    @Override
    public List<RepositoryInfo> getRepositories(String username) {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet("https://api.github.com/users/" + username + "/repos");
            request.setHeader("Accept", "application/vnd.github+json");

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
                    Gson gson = new Gson();
                    List<RepositoryInfo> repos = gson.fromJson(
                            responseString,
                            new TypeToken<List<RepositoryInfo>>(){}.getType()
                    );
                    log.info("{} repos found {}", username, repos);
                    return repos;
                } else {
                    log.info("{} reason: {}", statusCode, response.getStatusLine().getReasonPhrase());
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());

        }
        return new ArrayList<>();
    }
}
