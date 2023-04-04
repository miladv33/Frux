package com.example.frux.data.repository.safecall

import com.example.frux.data.map.delegate.failedmap.FailedMapperDelegate

class SafeCallDelegateImpl(private val failedMapperDelegate: FailedMapperDelegate) : SafeCallDelegate,
    FailedMapperDelegate by failedMapperDelegate {


    override suspend fun <T> safeCall(call: suspend () -> Result<T>): Result<T> {
        return try {
            call.invoke()
        } catch (e: Exception) {
            mapFailure(e)
        }
    }


}