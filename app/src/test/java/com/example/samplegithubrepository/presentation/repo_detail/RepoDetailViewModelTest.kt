package com.example.samplegithubrepository.presentation.repo_detail

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.samplegithubrepository.domain.exception.traceErrorException
import com.example.samplegithubrepository.domain.model.repo_detail.Owner
import com.example.samplegithubrepository.domain.model.repo_detail.RepoDetail

import com.example.samplegithubrepository.domain.repository.RepoDetailRepository

import com.example.samplegithubrepository.domain.usecase.RepoDetailUseCase

import com.example.samplegithubrepository.presentation.repo_list.BaseViewModelTest

import com.example.samplegithubrepository.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class RepoDetailViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineScope = BaseViewModelTest()


    @Mock
    private lateinit var repoDetailUseCase: RepoDetailUseCase

    @Mock
    private lateinit var repoDetail: RepoDetail

    private lateinit var gitRepoDetailState: GitRepoDetailState

    private lateinit var viewModel: RepoDetailViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        gitRepoDetailState = GitRepoDetailState()

        viewModel = RepoDetailViewModel(repoDetailUseCase)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getRepoDetail() = runBlocking {
        val repoDetailRepository: RepoDetailRepository =
            Mockito.mock(RepoDetailRepository::class.java)

        val flow = flow {
            emit(Resource.Loading())
            delay(10)
            val repoDetail1 = repoDetailRepository.getRepoDetail("rubinius/rubinius")
            emit(Resource.Success<RepoDetail>(repoDetail1))
            emit(Resource.Error<RepoDetail>(traceErrorException(Exception())))
        }

        whenever(
            repoDetailUseCase.invoke("rubinius/rubinius")
        ).thenReturn(
            flow
        )



        assertNotNull(viewModel.getRepoDetail())

    }


    }