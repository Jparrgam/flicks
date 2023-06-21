package com.foodie.flicks.model


import com.squareup.moshi.Json

data class UserRatingModelResponse(
    @Json(name = "count_negative")
    val countNegative: Double? = .0,
    @Json(name = "count_positive")
    val countPositive: Double? = .0,
    @Json(name = "score")
    val score: Double? = .0
)