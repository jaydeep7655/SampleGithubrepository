package com.example.samplegithubrepository.presentation.repo_list

import org.junit.Assert.*
import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.samplegithubrepository.domain.exception.traceErrorException
import com.example.samplegithubrepository.domain.model.repo_list.Owner
import com.example.samplegithubrepository.domain.model.repo_list.RepoListItem
import com.example.samplegithubrepository.domain.repository.RepoListRepository
import com.example.samplegithubrepository.domain.usecase.RepoListUseCase
import com.example.samplegithubrepository.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class RepoListViewModelTest{

   @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = BaseViewModelTest()

    @Mock
    private lateinit var repoListUseCase: RepoListUseCase

    @Mock
    private lateinit var repoList: List<RepoListItem>

    private lateinit var gitRepoListState: GitRepoListState

    private lateinit var viewModel: RepoListViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        gitRepoListState = GitRepoListState()

        viewModel = RepoListViewModel(repoListUseCase)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun getAllRepos() = runBlocking{

        val repoListRepository: RepoListRepository = Mockito.mock(RepoListRepository::class.java)

        val flow = flow {
            emit(Resource.Loading())
            delay(10)
            val repoList = repoListRepository.getRepoList().map {it}
            emit(Resource.Success<List<RepoListItem>>(repoList))
            emit(Resource.Error<List<RepoListItem>>(traceErrorException(Exception())))
        }



        whenever(
            repoListUseCase.invoke()
        ).thenReturn(
            flow
        )


        whenever(
            repoList[0]
        ).thenReturn(
            RepoListItem(
                description = "**Grit is no longer maintained. Check out libgit2/rugged.** Grit gives you object oriented read/write access to Git repositories via Ruby.",
                fork = false,
                full_name = "mojombo/grit",
                id = 1,
                name = "grit",
                owner = Owner(id=1, login="mojombo", starred_url="https://api.github.com/users/mojombo/starred{/owner}{/repo}", type="User", url="https://api.github.com/users/mojombo"),
                url = "https://api.github.com/repos/mojombo/grit"
            )
        )

       assertNotNull(viewModel.getRepoList())

    }

}