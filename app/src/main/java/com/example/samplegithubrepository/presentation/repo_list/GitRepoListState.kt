package com.example.samplegithubrepository.presentation.repo_list

import com.example.samplegithubrepository.domain.model.repo_list.RepoListItem
import com.example.samplegithubrepository.utils.ApiError


data class GitRepoListState(
    val isLoading: Boolean = false,
    val data: List<RepoListItem>? = null,
    val error: ApiError? = null
 )


