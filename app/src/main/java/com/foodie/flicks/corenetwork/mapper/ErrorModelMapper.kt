package com.foodie.flicks.corenetwork.mapper

import com.foodie.flicks.corenetwork.exception.ErrorModelException
import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

object ErrorModelMapper : ApiErrorModelMapper<ErrorModelException> {
    override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): ErrorModelException =
        ErrorModelException.ErrorExceptionWithMessage(
            apiErrorResponse.statusCode.code,
            apiErrorResponse.message()
        )
}