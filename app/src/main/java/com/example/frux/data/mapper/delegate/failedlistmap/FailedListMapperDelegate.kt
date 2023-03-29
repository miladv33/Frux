package com.example.frux.data.mapper.delegate.failedlistmap


/**
 * All mappers inherit the base mapper class
 *
 * @param T
 * @constructor Create empty Base mapper
 */
/* It's a base class for all mappers that will be used in the app */
interface FailedListMapperDelegate {
    /**
     * In the case of those mappers who work with a list of a model
     * If an exception is thrown, return a failure result.
     * @param exception Exception - The exception that was thrown
     */
    fun <T>listMapFailure(exception: Exception): Result<List<T>>
}