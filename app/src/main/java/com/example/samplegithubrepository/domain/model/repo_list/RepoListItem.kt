package com.example.samplegithubrepository.domain.model.repo_list

data class RepoListItem(
    val description: String,
    val fork: Boolean,
    val full_name: String,
    val id: Int,
    val name: String,
    val owner: Owner,
    val url: String
)