package com.example.samplegithubrepository.utils

sealed class Resource<T>(val data: T? = null, val message: ApiError? = null) {

    class Success<T>(data: T) : Resource<T>(data)

    class Error<T>(message: ApiError, data: T? = null) : Resource<T>(data, message)

    class Loading<T>(data: T? = null) : Resource<T>(data)
}