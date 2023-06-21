package com.foodie.flicks.model

import com.squareup.moshi.Json

data class IngredientModelResponse(
    @Json(name = "created_at")
    val createdAt: Int = 0,
    @Json(name = "display_plural")
    val displayPlural: String? = "",
    @Json(name = "display_singular")
    val displaySingular: String? = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "updated_at")
    val updatedAt: Int = 0
)