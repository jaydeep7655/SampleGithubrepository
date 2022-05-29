package com.example.samplegithubrepository.domain.repository

import com.example.samplegithubrepository.domain.model.repo_list.RepoListItem

interface RepoListRepository {
    suspend fun getRepoList():List<RepoListItem>
}