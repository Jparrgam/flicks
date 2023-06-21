package com.foodie.flicks.corenetwork.exception

sealed interface ErrorModelException {
    data class ErrorExceptionWithMessage(
        val statusCode: Int,
        val message: String
    ) :
        ErrorModelException

    object UnrecognizedError :
        ErrorModelException
}
