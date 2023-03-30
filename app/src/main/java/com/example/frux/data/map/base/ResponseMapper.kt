package com.example.frux.data.map.base

import com.example.frux.data.enum.Error
import com.example.frux.data.map.delegate.failedmap.FailedMapperDelegate
import com.example.frux.data.model.base.CustomException
import com.example.frux.data.model.base.Model
import retrofit2.Response

interface ResponseMapper<DTO, T : Model> {
    val failedMapperDelegate: FailedMapperDelegate

    fun map(input: Response<DTO>?): Result<T> {
        return input?.body()?.let { createModelFromDTO(input) }
            ?.let { Result.success(it) }
            ?: failedMapperDelegate.mapFailure(CustomException(Error.NullObject))
    }

    fun createModelFromDTO(input: Response<DTO>): T
}