package com.example.samplegithubrepository.domain.model.repo_detail

data class RepoDetail(
    val forks: Int,
    val forks_count: Int,
    val full_name: String,
    val id: Int,
    val name: String,
    val stargazersCount: Int,
    val network_count: Int,
    val open_issues: Int,
    val open_issues_count: Int,
    val owner: Owner,
    val subscribers_count: Int,
    val topics: List<String>,
    val watchers: Int,
    val watchers_count: Int,
    val description:String
)