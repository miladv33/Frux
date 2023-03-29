package com.example.frux.data.mapper.delegate.failedmap


/**
 * All mappers inherit the base mapper class
 *
 * @param T
 * @constructor Create empty Base mapper
 */
/* It's a base class for all mappers that will be used in the app */
interface FailedMapperDelegate {
    /**
     * In the case of those mappers who work with model classes
     * If an exception is thrown, return a failure result.
     *
     * @param exception The exception that was thrown
     */
    fun <T>mapFailure(exception: Exception): Result<T>
}