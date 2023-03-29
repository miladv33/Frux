package com.example.frux.data.map.base

import com.example.frux.data.model.base.Model


/**
 * This is a mapper that maps a list of DTOs to a list of models
 * In the case of those mappers who work with a list of a model
 * @param DTO
 * @param T
 * @constructor Create empty List response mapper
 */
interface ListResponseMapper<DTO, T : Model> : ResponseMapper<DTO, T> {
    /**
     * Create a result object from a list
     *
     * @param input
     * @return
     */
    fun mapList(input: List<DTO>?): Result<List<T>>
}