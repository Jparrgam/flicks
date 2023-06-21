package com.foodie.flicks.feeddata.respository

import arrow.core.Either
import com.foodie.flicks.corenetwork.exception.ErrorModelException
import com.foodie.flicks.feeddata.model.FeedVideoDetailUiModel
import kotlinx.coroutines.flow.Flow

interface FeedListRepository {
    suspend fun getFeedList(): Flow<Either<ErrorModelException, List<FeedVideoDetailUiModel>>>
}