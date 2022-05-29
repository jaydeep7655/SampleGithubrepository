package com.example.samplegithubrepository.presentation.repo_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.samplegithubrepository.domain.model.repo_list.RepoListItem
import com.example.samplegithubrepository.domain.usecase.RepoListUseCase
import com.example.samplegithubrepository.utils.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class RepoListViewModel  constructor(
    private val reposListUseCase: RepoListUseCase
) : ViewModel() {


    private var showProgressBar: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private val _gitRepoList = MutableStateFlow<GitRepoListState>(GitRepoListState())
    val getRepoList: StateFlow<GitRepoListState>
        get() = _gitRepoList

    val searchText = mutableStateOf("")

    public var matchedUsers: MutableStateFlow<List<RepoListItem>> =
        MutableStateFlow(arrayListOf())

    var  alRepoList:List<RepoListItem> = arrayListOf()


    init {
        getRepoList()
    }

    fun getRepoList() {
            reposListUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    _gitRepoList.value = GitRepoListState(isLoading = true)
                }
                is Resource.Success -> {
                    _gitRepoList.value = GitRepoListState(data = it.data)
                }
                is Resource.Error -> {
                    _gitRepoList.value = GitRepoListState(error = it.message)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun onSearchTextChanged(changedSearchText: String) {
        searchText.value = changedSearchText
        if (changedSearchText.isEmpty()) {
            matchedUsers.value = arrayListOf()
            return
        }
        val usersFromSearch = alRepoList.filter { x ->
            x.name.contains(changedSearchText, true) ||
                    x.description.contains(changedSearchText, true
            )
        }
        matchedUsers.value = usersFromSearch
    }
    fun onClearClick() {
        searchText.value = ""
        matchedUsers.value = arrayListOf()
    }

}