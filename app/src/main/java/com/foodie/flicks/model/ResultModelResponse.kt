package com.foodie.flicks.model


import com.squareup.moshi.Json

data class ResultModelResponse(
    @Json(name = "item")
    val item: DetailsModelResponse,
    @Json(name = "type")
    val type: String = ""
)