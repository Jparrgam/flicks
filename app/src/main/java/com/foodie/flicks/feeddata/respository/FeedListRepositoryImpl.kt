package com.foodie.flicks.feeddata.respository

import arrow.core.Either
import arrow.core.left
import com.foodie.flicks.corenetwork.dispatcher.Dispatcher
import com.foodie.flicks.corenetwork.dispatcher.FoodieFlicksDispatcher
import com.foodie.flicks.corenetwork.exception.ErrorModelException
import com.foodie.flicks.corenetwork.mapper.ErrorModelMapper
import com.foodie.flicks.daggerscopes.AppScope
import com.foodie.flicks.daggerscopes.SingleIn
import com.foodie.flicks.feeddata.FeedListService
import com.foodie.flicks.feeddata.mapper.FeedListMapper
import com.foodie.flicks.feeddata.model.FeedVideoDetailUiModel
import com.skydoves.sandwich.retry.RetryPolicy
import com.skydoves.sandwich.retry.runAndRetry
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.squareup.anvil.annotations.ContributesBinding
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class FeedListRepositoryImpl @Inject constructor(
    private val feedListService: FeedListService,
    private val retryPolicy: RetryPolicy,
    @Dispatcher(FoodieFlicksDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) : FeedListRepository {
    override suspend fun getFeedList(): Flow<Either<ErrorModelException, List<FeedVideoDetailUiModel>>> =
        flow {
            val apiResponse = runAndRetry(retryPolicy) { _, _ -> feedListService.getFeedList() }

            apiResponse.suspendOnSuccess(FeedListMapper) {
                emit(this)
            }
                .suspendOnError(ErrorModelMapper) {
                    emit(this.left())
                }
                .suspendOnException {
                    emit(ErrorModelException.UnrecognizedError.left())
                }
        }
            .flowOn(
                ioDispatcher
            )
}