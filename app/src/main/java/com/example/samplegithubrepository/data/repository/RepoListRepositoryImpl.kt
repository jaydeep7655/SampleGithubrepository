package com.example.samplegithubrepository.data.repository

import com.example.samplegithubrepository.data.mapper.toDomain
import com.example.samplegithubrepository.data.remote.ApiService
import com.example.samplegithubrepository.domain.model.repo_list.RepoListItem
import com.example.samplegithubrepository.domain.repository.RepoListRepository

class RepoListRepositoryImpl(private val apiService: ApiService):RepoListRepository {
    override suspend fun getRepoList(): List<RepoListItem> {
       val response= apiService.getRepoList()
        return response.toDomain()

    }
}



