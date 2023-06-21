package com.foodie.flicks.model

import com.squareup.moshi.Json

data class TotalTimeTierModelResponse(
    @Json(name = "display_tier")
    val displayTier: String = "",
    @Json(name = "tier")
    val tier: String = ""
)