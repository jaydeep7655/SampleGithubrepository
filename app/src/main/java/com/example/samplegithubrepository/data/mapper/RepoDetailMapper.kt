package com.example.samplegithubrepository.data.mapper

import com.example.samplegithubrepository.data.model.repo_detail_dto.OwnerDTO
import com.example.samplegithubrepository.data.model.repo_detail_dto.RepoDetailDTO
import com.example.samplegithubrepository.domain.model.repo_detail.Owner

import com.example.samplegithubrepository.domain.model.repo_detail.RepoDetail

fun RepoDetailDTO.toDomain(): RepoDetail {
    return RepoDetail(
        forks = forks ?: 0,
        forks_count = forksCount ?: 0,
        full_name = fullName ?: "",
        id = id ?: 0,
        name = name ?: "",
        stargazersCount = stargazersCount ?: 0,
        network_count = networkCount?:0,
        open_issues = openIssues?:0,
        open_issues_count = openIssuesCount?:0,
        owner =  ownerDTO?.toDomain()?:Owner("","") ,
        subscribers_count = subscribersCount?:0,
        topics = (topics?: ArrayList<String>()) as List<String>,
        watchers = watchers?:0,
        watchers_count = watchersCount?:0,
        description = description?:""
    )

}

fun OwnerDTO.toDomain(): Owner {
    return Owner(
        avatar_url=avatarUrl?:"",
        owner_login=login?:""


    )
}
