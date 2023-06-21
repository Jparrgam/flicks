package com.foodie.flicks.similaritiesdata.mapper

import com.foodie.flicks.model.DetailsModelResponse
import com.foodie.flicks.similaritiesdata.ui.SimilaritiesUiModel

fun List<DetailsModelResponse>.toSimilaritiesUiModel():
        List<SimilaritiesUiModel> =
    this.map {
        SimilaritiesUiModel(
            name = it.name,
            thumbnailUrl = it.thumbnailUrl
        )
    }
