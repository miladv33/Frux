package com.example.frux.data.mapper.utils

import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.memberProperties

object MapperUtils {
    /* Creating a map of the source object's properties and then using that map to create a new
       object of the destination class. */
    fun <T : Any> map(
        source: Any,
        destClass: KClass<T>,
        customFields: Map<String, Any> = emptyMap()
    ): T {

        val properties = source.javaClass.kotlin.memberProperties.associateBy { it.name }
        return destClass.constructors.first().callBy(
            destClass.constructors.first().parameters.associate { param ->
                val paramName = param.name
                val value = when (customFields[paramName] != null) {
                    true -> customFields[paramName]
                    false -> properties[paramName]?.get(source)
                        ?: handleMissingParam(param, source)
                }

                param to value
            }.filter { it.value != null }
        )
    }

    /**
     * If the parameter is not optional, throw an exception, otherwise return null
     *
     * @param parameter The parameter that we're trying to resolve.
     * @param source The object that contains the parameters.
     * @return A function that takes a KParameter and an Any and returns an Any?
     */
    private fun handleMissingParam(parameter: KParameter, source: Any): Any? {
        if (!parameter.isOptional) {
            throw IllegalArgumentException("`${parameter.name}` parameter is not found in $source")
        }
        return null
    }
}