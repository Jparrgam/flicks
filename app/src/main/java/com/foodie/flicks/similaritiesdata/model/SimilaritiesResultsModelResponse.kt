package com.foodie.flicks.similaritiesdata.model

import com.foodie.flicks.model.DetailsModelResponse
import com.squareup.moshi.Json

data class SimilaritiesResultsModelResponse(
    @Json(name = "results")
    val similaritiesResults: List<DetailsModelResponse> = listOf()
)
