package com.example.samplegithubrepository.presentation.repo_detail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplegithubrepository.domain.model.repo_detail.Owner
import com.example.samplegithubrepository.domain.model.repo_detail.RepoDetail
import com.example.samplegithubrepository.domain.usecase.RepoDetailUseCase
import com.example.samplegithubrepository.presentation.repo_list.GitRepoListState
import com.example.samplegithubrepository.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class RepoDetailViewModel constructor(
    private val repoDetailUseCase: RepoDetailUseCase
) : ViewModel() {

    val full_name = mutableStateOf("")

    val mForks = mutableStateOf(0)
    val mForksCount = mutableStateOf(0)
    val mFullName = mutableStateOf("")
    val mId = mutableStateOf(0)
    val mName = mutableStateOf("")
    val mStargazersCount = mutableStateOf(0)
    val mNetworkCount = mutableStateOf(0)
    val mOpenIssues = mutableStateOf(0)
    val mOpenIssuesCount = mutableStateOf(0)
    val mSubscribersCount = mutableStateOf(0)
    val mWatchers = mutableStateOf(0)
    val mWatchersCount = mutableStateOf(0)
    val mAvatarurl = mutableStateOf("")
    val mOwnerLogin = mutableStateOf("")
    val mDescription= mutableStateOf("")

    private val _repoDetail = MutableStateFlow<GitRepoDetailState>(GitRepoDetailState())
    val repoDetail: StateFlow<GitRepoDetailState>
        get() = _repoDetail

    fun getRepoDetail() {
        repoDetailUseCase(full_name.value).onEach {
            when (it) {
                is Resource.Loading -> {
                    _repoDetail.value = GitRepoDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    setData(it.data)
                    _repoDetail.value = GitRepoDetailState(data = it.data)
                }
                is Resource.Error -> {
                    _repoDetail.value = GitRepoDetailState(error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun setData(data: RepoDetail?) {

        data?.let {
            mForks.value = it.forks
            mForksCount.value = it.forks_count
            mFullName.value = it.full_name
            mId.value = it.id
            mName.value = it.name
            mStargazersCount.value = it.stargazersCount
            mNetworkCount.value = it.network_count
            mOpenIssues.value = it.open_issues
            mOpenIssuesCount.value = it.open_issues_count
            mSubscribersCount.value = it.subscribers_count
            mWatchers.value = it.watchers
            mWatchersCount.value = it.watchers_count
            mAvatarurl.value = it.owner.avatar_url
            mOwnerLogin.value = it.owner.owner_login
            mDescription.value=it.description
        }


    }


}