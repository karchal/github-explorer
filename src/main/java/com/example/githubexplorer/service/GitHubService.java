package com.example.githubexplorer.service;

import com.example.githubexplorer.model.RepositoryInfo;

import java.util.List;

public interface GitHubService {

    List<RepositoryInfo> getRepositories(String userName);

}
