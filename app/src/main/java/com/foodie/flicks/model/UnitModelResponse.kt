package com.foodie.flicks.model

import com.squareup.moshi.Json

data class UnitModelResponse(
    @Json(name = "abbreviation")
    val abbreviation: String = "",
    @Json(name = "display_plural")
    val displayPlural: String = "",
    @Json(name = "display_singular")
    val displaySingular: String = "",
    @Json(name = "name")
    val name: String = "",
    @Json(name = "system")
    val system: String = ""
)