package com.foodie.flicks.model

import com.squareup.moshi.Json

data class TagsModelResponse(
    @Json(name = "display_name")
    val displayName: String = "",
    @Json(name = "id")
    val id: Int = 0
)