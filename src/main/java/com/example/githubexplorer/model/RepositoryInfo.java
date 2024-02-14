package com.example.githubexplorer.model;

import lombok.Data;

@Data
public class RepositoryInfo {
    private final String name;
    private final Owner owner;
    private final boolean fork;
}
