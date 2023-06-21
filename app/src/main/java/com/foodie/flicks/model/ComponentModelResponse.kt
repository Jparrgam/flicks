package com.foodie.flicks.model

import com.squareup.moshi.Json

data class ComponentModelResponse(
    @Json(name = "extra_comment")
    val extraComment: String = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "ingredient")
    val feedIngredientModelResponse: IngredientModelResponse,
    @Json(name = "measurements")
    val measurements: List<MeasurementModelResponse> = listOf(),
    @Json(name = "position")
    val position: Int = 0,
    @Json(name = "raw_text")
    val rawText: String = ""
)