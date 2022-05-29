package com.example.samplegithubrepository.domain.model.repo_list

data class Owner(
    val id: Int,
    val login: String,
    val starred_url: String,
    val type: String,
    val url: String
)