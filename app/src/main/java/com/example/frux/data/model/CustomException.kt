package com.example.frux.data.model

import com.example.frux.data.enum.Error

/**
 *
 * deal with any kinds of exceptions
 * @property error Error
 * @constructor
 */
data class CustomException(
    val error: Error
    ):Exception()