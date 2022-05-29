package com.example.samplegithubrepository.presentation.repo_detail

import com.example.samplegithubrepository.domain.model.repo_detail.RepoDetail
import com.example.samplegithubrepository.domain.model.repo_list.RepoListItem
import com.example.samplegithubrepository.utils.ApiError


data class GitRepoDetailState(
    val isLoading: Boolean = false,
    val data: RepoDetail? = null,
    val error: ApiError? = null
 )


