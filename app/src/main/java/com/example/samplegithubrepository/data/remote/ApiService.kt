package com.example.samplegithubrepository.data.remote

import com.example.samplegithubrepository.data.model.repo_detail_dto.RepoDetailDTO
import com.example.samplegithubrepository.data.model.repo_list_dto.RepoListDTOItem
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("repositories")
    suspend fun getRepoList(): List<RepoListDTOItem>

    @GET("repos/{fullName}")
    suspend fun getRepoDetail(@Path("fullName", encoded=true) fullName: String):RepoDetailDTO

}