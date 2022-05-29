package com.example.samplegithubrepository.di

import com.example.samplegithubrepository.data.remote.ApiService
import com.example.samplegithubrepository.data.repository.RepoDetailRepositoryImpl
import com.example.samplegithubrepository.data.repository.RepoListRepositoryImpl
import com.example.samplegithubrepository.domain.repository.RepoDetailRepository
import com.example.samplegithubrepository.domain.repository.RepoListRepository
import com.example.samplegithubrepository.domain.usecase.RepoDetailUseCase
import com.example.samplegithubrepository.domain.usecase.RepoListUseCase
import com.example.samplegithubrepository.presentation.repo_detail.RepoDetailViewModel
import com.example.samplegithubrepository.presentation.repo_list.RepoListViewModel
import org.koin.dsl.module

import org.koin.androidx.viewmodel.dsl.viewModel

val AppModule = module {
    viewModel { RepoListViewModel(get()) }
    viewModel { RepoDetailViewModel(get()) }
    factory {  createRepoListRepository(get())}
    factory{ createGetRepoListUseCase(get())}
    factory{ createRepoDetailRepository(get())}
    factory{ createGetRepoDetailUseCase(get())}

}

fun createRepoListRepository(apiService: ApiService): RepoListRepository {
    return RepoListRepositoryImpl(apiService)
}

fun createGetRepoListUseCase(repository: RepoListRepository): RepoListUseCase {
    return RepoListUseCase(repository)
}

fun createRepoDetailRepository(apiService: ApiService): RepoDetailRepository {
    return RepoDetailRepositoryImpl(apiService)
}

fun createGetRepoDetailUseCase(repository:RepoDetailRepository):RepoDetailUseCase{
    return RepoDetailUseCase(repository)
}