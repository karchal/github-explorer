package com.example.githubexplorer.controller;

import com.example.githubexplorer.model.RepositoryInfo;
import com.example.githubexplorer.service.GitHubService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/api/repositories")
    public ResponseEntity<List<RepositoryInfo>> getRepositories(@RequestParam String username){
        List<RepositoryInfo> repositories = gitHubService.getRepositories(username);
        return new ResponseEntity<>(
                repositories,
                HttpStatusCode.valueOf(200)
        );
    }
}
