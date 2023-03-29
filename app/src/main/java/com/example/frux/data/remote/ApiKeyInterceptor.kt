package com.example.frux.data.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val originalRequest: Request = chain.request()
            val originalUrl = originalRequest.url
            val modifiedUrl = originalUrl.newBuilder()
                .addQueryParameter("key", apiKey)
                .build()
            val modifiedRequest = originalRequest.newBuilder()
                .url(modifiedUrl)
                .build()
            return chain.proceed(modifiedRequest)
        } catch (e: Exception) {
            return chain.proceed(chain.request())
        }
    }
}