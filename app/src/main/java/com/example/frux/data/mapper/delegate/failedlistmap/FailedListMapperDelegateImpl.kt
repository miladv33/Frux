package com.example.frux.data.mapper.delegate.failedlistmap

class FailedListMapperDelegateImpl: FailedListMapperDelegate {
    override fun <T> listMapFailure(exception: Exception): Result<List<T>> = Result.failure(exception)
}