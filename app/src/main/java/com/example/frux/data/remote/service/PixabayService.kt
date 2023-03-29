package com.example.frux.data.remote.service

import com.example.frux.data.model.PixabayImage
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayService {
    @GET
    suspend fun getImages(
        @Query("q") query: String?,
        @Query("image_type") imageType: String?
    ): Response<PixabayImage?>?
}