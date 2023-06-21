package com.foodie.flicks.similaritiesdata.respository

import arrow.core.Either
import com.foodie.flicks.corenetwork.exception.ErrorModelException
import com.foodie.flicks.similaritiesdata.ui.SimilaritiesUiModel
import kotlinx.coroutines.flow.Flow

interface SimilaritiesRepository {
    suspend fun getRecipesSimilarities(
        recipeId: Int
    ): Flow<Either<ErrorModelException, List<SimilaritiesUiModel>>>
}