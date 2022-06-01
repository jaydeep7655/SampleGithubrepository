package com.example.samplegithubrepository.domain.usecase

import com.example.samplegithubrepository.domain.exception.traceErrorException
import com.example.samplegithubrepository.domain.model.repo_list.RepoListItem
import com.example.samplegithubrepository.domain.repository.RepoListRepository
import com.example.samplegithubrepository.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

class RepoListUseCase constructor(private val repository: RepoListRepository) {
    operator fun invoke(): Flow<Resource<List<RepoListItem>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getRepoList()
            emit(Resource.Success(data = response as List<RepoListItem>))
        } catch (e: HttpException) {
            emit(Resource.Error(traceErrorException(e)))
        }catch (e: UnknownHostException){
           emit(Resource.Error(traceErrorException(e)))
        } catch (e: IOException) {
           emit(Resource.Error(traceErrorException(e)))
        } catch (e: Exception) {
            emit(Resource.Error(traceErrorException(e)))
        }
    }
}