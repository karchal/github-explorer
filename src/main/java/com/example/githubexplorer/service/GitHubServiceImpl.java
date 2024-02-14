package com.example.githubexplorer.service;

import com.example.githubexplorer.client.GitHubClient;
import com.example.githubexplorer.model.RepositoryInfo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
public class GitHubServiceImpl implements GitHubService {

    private final GitHubClient client;

    public GitHubServiceImpl(GitHubClient client) {
        this.client = client;
    }

    @Override
    public List<RepositoryInfo> getRepositories(String username) {

        List<RepositoryInfo> repos = client.getRepoList(username);

        if(repos.size() == 0) { return repos; }

        List<RepositoryInfo> notForkRepos = repos
                .stream()
                .filter(r -> !r.isFork())
                .collect(Collectors.toList());

        return notForkRepos;
    }

}
