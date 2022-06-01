package com.example.samplegithubrepository.domain.usecase

import com.example.samplegithubrepository.domain.exception.traceErrorException
import com.example.samplegithubrepository.domain.model.repo_detail.RepoDetail
import com.example.samplegithubrepository.domain.model.repo_list.RepoListItem
import com.example.samplegithubrepository.domain.repository.RepoDetailRepository
import com.example.samplegithubrepository.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException


class RepoDetailUseCase constructor(private val repository: RepoDetailRepository) {
    operator fun invoke(fullName: String): Flow<Resource<RepoDetail>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getRepoDetail(fullName)
            emit(Resource.Success(data = response))
        } catch (e: HttpException) {
            emit(Resource.Error(traceErrorException(e)))
        } catch (e: UnknownHostException) {
            emit(Resource.Error(traceErrorException(e)))
        } catch (e: IOException) {
            emit(Resource.Error(traceErrorException(e)))
        } catch (e: Exception) {
            emit(Resource.Error(traceErrorException(e)))
        }

    }
}