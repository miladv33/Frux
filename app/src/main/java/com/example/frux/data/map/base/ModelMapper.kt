package com.example.frux.data.map.base
import com.example.frux.data.enum.Error
import com.example.frux.data.map.delegate.failedmap.FailedMapperDelegate
import com.example.frux.data.model.base.CustomException
import com.example.frux.data.model.base.Model

/**
 * In the case of those mappers who work with model classes
 *
 * @param DTO
 * @param T
 * @constructor Create empty Model mapper
 */
interface ModelMapper<DTO, T : Model>  {
    var failedMapperDelegate: FailedMapperDelegate
    /**
     * Main function. This function gets a DTO and checks if it is not null, so it creates a Result from it
     * @param input The DTO object that we want to map to our domain object.
     * @return Result<T>
     */
    fun map(input: DTO?): Result<T> {
        return if (input != null) {
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
    private fun checkNullable(input: DTO?): T {
        return input?.let {
            return createModelFromDTO(it)
        } ?: kotlin.run {
            Model() as T
        }
    }

    /**
     * Create model from dto
     *
     * @param input
     * @return
     */
    fun createModelFromDTO(input:DTO): T
}