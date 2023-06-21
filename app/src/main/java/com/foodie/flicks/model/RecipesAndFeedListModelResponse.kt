package com.foodie.flicks.model

import com.squareup.moshi.Json

data class RecipesAndFeedListModelResponse(
    @Json(name = "results")
    val feedDetailsModelResponses: List<ResultModelResponse> = listOf()
)