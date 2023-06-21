package com.foodie.flicks.model


import com.squareup.moshi.Json

data class PriceModelResponse(
    @Json(name = "portion")
    val portion: Int = 0,
    @Json(name = "total")
    val total: Int = 0,
    @Json(name = "updated_at")
    val updatedAt: String = ""
)