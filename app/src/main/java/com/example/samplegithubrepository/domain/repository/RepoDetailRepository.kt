package com.example.samplegithubrepository.domain.repository

import com.example.samplegithubrepository.domain.model.repo_detail.RepoDetail

interface RepoDetailRepository {
    suspend fun getRepoDetail(fullName:String):RepoDetail
}