package com.example.frux.data.map.base

import com.example.frux.data.enum.Error
import com.example.frux.data.map.delegate.failedmap.FailedMapperDelegate
import com.example.frux.data.model.base.CustomException
import com.example.frux.data.model.base.Model
import retrofit2.Response

/**
 *
 * @param DTO
 * @param T : Model
 */
interface ResponseMapper<DTO, T : Model> {
    val failedMapperDelegate: FailedMapperDelegate

    /**
     * for null check and map the result
     * @param input DTO?
     * @return Result<T>
     */
    fun map(input: Response<DTO>?): Result<T> {
        return if (input?.body() != null) {
            Result.success(checkNullable(input))
        } else {
            failedMapperDelegate.mapFailure(CustomException(Error.NullObject))
        }
    }

    /**
     * check input is null or not
     * @param input DTO?
     * @return T
     */
    private fun checkNullable(input: Response<DTO>?): T {
        return input?.let {
            return createModelFromDTO(it)
        } ?: kotlin.run {
            Model() as T
        }
    }

    /**
     * change DTO to model
     * @param input DTO
     * @return T
     */
    fun createModelFromDTO(input: Response<DTO>): T

}