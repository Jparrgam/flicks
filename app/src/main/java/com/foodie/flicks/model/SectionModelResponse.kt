package com.foodie.flicks.model

import com.squareup.moshi.Json

data class SectionModelResponse(
    @Json(name = "components")
    val feedComponentModelResponses: List<ComponentModelResponse> = listOf(),
    @Json(name = "name")
    val name: Any? = null,
    @Json(name = "position")
    val position: Int = 0
)