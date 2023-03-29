package com.example.frux.data.map.delegate.failedlistmap

class FailedListMapperDelegateImpl: FailedListMapperDelegate {
    override fun <T> listMapFailure(exception: Exception): Result<List<T>> = Result.failure(exception)
}