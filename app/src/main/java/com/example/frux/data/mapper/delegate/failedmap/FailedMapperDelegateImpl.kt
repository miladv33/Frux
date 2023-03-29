package com.example.frux.data.mapper.delegate.failedmap

class FailedMapperDelegateImpl: FailedMapperDelegate {
    override fun <T>mapFailure(exception: Exception): Result<T> = Result.failure(exception)
}