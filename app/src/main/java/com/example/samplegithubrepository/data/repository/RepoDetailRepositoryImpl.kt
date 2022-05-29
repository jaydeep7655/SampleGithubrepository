package com.example.samplegithubrepository.data.repository

import com.example.samplegithubrepository.data.mapper.toDomain
import com.example.samplegithubrepository.data.remote.ApiService
import com.example.samplegithubrepository.domain.model.repo_detail.RepoDetail
import com.example.samplegithubrepository.domain.model.repo_list.RepoListItem
import com.example.samplegithubrepository.domain.repository.RepoDetailRepository
import com.example.samplegithubrepository.domain.repository.RepoListRepository

class RepoDetailRepositoryImpl(private val apiService: ApiService):RepoDetailRepository {
    override suspend fun getRepoDetail(fullName: String): RepoDetail {
        val response= apiService.getRepoDetail(fullName)
        return response.toDomain()
    }

}



