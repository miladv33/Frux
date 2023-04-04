package com.example.frux.data.repository.safecall

interface SafeCallDelegate {

    suspend fun <T>safeCall(call: suspend () -> Result<T>): Result<T>
}