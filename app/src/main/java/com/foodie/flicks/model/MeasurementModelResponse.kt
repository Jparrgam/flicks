package com.foodie.flicks.model

import com.squareup.moshi.Json

data class MeasurementModelResponse(
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "quantity")
    val quantity: String = "",
    @Json(name = "unit")
    val unit: UnitModelResponse
)