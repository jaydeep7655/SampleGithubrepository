package com.example.samplegithubrepository.presentation.repo_list

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.samplegithubrepository.R
import com.example.samplegithubrepository.domain.model.repo_list.RepoListItem
import com.example.samplegithubrepository.presentation.composable_view.*
import com.example.samplegithubrepository.presentation.repo_detail.RepoDetailActivity
import com.example.samplegithubrepository.ui.theme.SampleGithubrepositoryTheme
import com.example.samplegithubrepository.utils.Constants
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


import org.koin.androidx.viewmodel.ext.android.viewModel


class RepoListActivity : ComponentActivity() {
    private val repoListViewModel: RepoListViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleGithubrepositoryTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Top,
                    ) {
                        HomeTopAppBar(text = getString(R.string.home_title)) {
                        }
                        MainScreen()
                        InitCallObservable()
                    }
                }
            }
        }

    }
    @Composable
    private fun InitCallObservable() {
        val state by repoListViewModel.getRepoList.collectAsState()
        if (state.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        if(state.error?.error == true) {
            CommonError(text = state.error!!.getErrorMessage(), onRefreshClick = { repoListViewModel.getRepoList() })
        }

        state.data?.let {
            if(it.size>0) {
                repoListViewModel.alRepoList=it
                if(repoListViewModel.searchText.value.isEmpty()){
                    LazyColumnWithSelection(it)
                }else{
                    LazyColumnWithSelection(repoListViewModel.matchedUsers.value)
                }

            } else {
                NothingHere(resources.getString(R.string.no_record))
            }
        }

    }

    @Composable
    private fun LazyColumnWithSelection(item: List<RepoListItem>) {
        var selectedIndex by remember { mutableStateOf(0) }
        val onItemClick = { index: Int -> selectedIndex = index }
        var refreshing by remember { mutableStateOf(false) }
        LaunchedEffect(refreshing) {
            if (refreshing) {
              repoListViewModel.getRepoList()
                refreshing = false
            }
        }

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing = refreshing),
            onRefresh = { refreshing = true }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
            ) {
                items(item.size) { index ->
                    RepoItem(item[index], index) {
                        val intent = Intent(this@RepoListActivity, RepoDetailActivity::class.java)
                        intent.putExtra(Constants.REPOLIST_TO_REPODETAIL, item[index].full_name)
                        startActivity(intent)
                    }
                }
            }
        }
    }
    @SuppressLint("StateFlowValueCalledInComposition")
    @Composable
    fun MainScreen() {
        SearchViewRepoSearch(query = repoListViewModel.searchText.value,
            onSearchTextChanged = { repoListViewModel.onSearchTextChanged(it) },
            onClearClick = { repoListViewModel.onClearClick() },
            placeHolder = stringResource(R.string.search)
                    )
    }

}

