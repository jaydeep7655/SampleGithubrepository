package com.example.samplegithubrepository.domain.exception


import com.example.samplegithubrepository.utils.ApiError
import com.example.samplegithubrepository.utils.UNKNOWN_ERROR_MESSAGE
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

fun traceErrorException(throwable: Throwable?): ApiError {

    return when (throwable) {

        is HttpException -> {
            when (throwable.code()) {
                400 -> ApiError(
                    throwable.message(),
                    throwable.code(),
                    ApiError.ErrorStatus.BAD_REQUEST,
                    true
                )
                401 -> ApiError(
                    throwable.message(),
                    throwable.code(),
                    ApiError.ErrorStatus.UNAUTHORIZED,
                    true
                )
                403 -> ApiError(
                    throwable.message(),
                    throwable.code(),
                    ApiError.ErrorStatus.FORBIDDEN,
                    true
                )
                404 -> ApiError(
                    throwable.message(),
                    throwable.code(),
                    ApiError.ErrorStatus.NOT_FOUND,
                    true
                )
                405 -> ApiError(
                    throwable.message(),
                    throwable.code(),
                    ApiError.ErrorStatus.METHOD_NOT_ALLOWED,
                    true
                )
                409 -> ApiError(
                    throwable.message(),
                    throwable.code(),
                    ApiError.ErrorStatus.CONFLICT,
                    true
                )
                500 -> ApiError(
                    throwable.message(),
                    throwable.code(),
                    ApiError.ErrorStatus.INTERNAL_SERVER_ERROR,
                    true
                )
                else -> ApiError(
                    UNKNOWN_ERROR_MESSAGE,
                    0,
                    ApiError.ErrorStatus.UNKNOWN_ERROR,
                    true
                )
            }
        }

        is SocketTimeoutException -> {
            ApiError(throwable.message, ApiError.ErrorStatus.TIMEOUT)
        }

        is IOException -> {
            ApiError(throwable.message, ApiError.ErrorStatus.NO_CONNECTION)
        }

        else -> ApiError(UNKNOWN_ERROR_MESSAGE, 0, ApiError.ErrorStatus.UNKNOWN_ERROR,true)
    }
}