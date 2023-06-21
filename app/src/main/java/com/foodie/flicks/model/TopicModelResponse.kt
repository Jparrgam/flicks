package com.foodie.flicks.model

import com.squareup.moshi.Json

data class TopicModelResponse(
    @Json(name = "name")
    val name: String = "",
    @Json(name = "slug")
    val slug: String = ""
)