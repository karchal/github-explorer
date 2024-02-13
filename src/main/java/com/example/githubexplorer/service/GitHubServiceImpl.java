package com.example.githubexplorer.service;

import com.example.githubexplorer.client.GitHubClient;
import com.example.githubexplorer.model.RepositoryInfo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class GitHubServiceImpl implements GitHubService {

    private final GitHubClient client;

    public GitHubServiceImpl(GitHubClient client) {
        this.client = client;
    }

    @Override
    public List<RepositoryInfo> getRepositories(String username) {
        String endpoint = username + "/repos";
        return client.getList(endpoint);
    }
}
