package com.foodie.flicks.feeddata.mapper

import arrow.core.Either
import com.foodie.flicks.feeddata.model.FeedVideoDetailUiModel
import com.foodie.flicks.model.RecipesAndFeedListModelResponse
import com.foodie.flicks.model.ResultModelResponse
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ApiSuccessModelMapper

object FeedListMapper :
    ApiSuccessModelMapper<RecipesAndFeedListModelResponse, Either.Right<List<FeedVideoDetailUiModel>>> {
    override fun map(
        apiSuccessResponse: ApiResponse.Success<RecipesAndFeedListModelResponse>
    ): Either.Right<List<FeedVideoDetailUiModel>> =
        Either.Right(
            apiSuccessResponse.data.feedDetailsModelResponses.map {
                it.feedToUiModel()
            })

    private fun ResultModelResponse.feedToUiModel(): FeedVideoDetailUiModel =
        FeedVideoDetailUiModel(
            videoUrl = item.originalVideoUrl,
            score = item.userRatings.score,
            description = item.description,
            recipesId = item.canonicalId,
            thumbnailUrl = item.thumbnailUrl
        )
}