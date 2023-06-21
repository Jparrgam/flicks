package com.foodie.flicks.similaritiesdata.respository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.foodie.flicks.corenetwork.dispatcher.Dispatcher
import com.foodie.flicks.corenetwork.dispatcher.FoodieFlicksDispatcher
import com.foodie.flicks.corenetwork.exception.ErrorModelException
import com.foodie.flicks.corenetwork.exception.ErrorModelException.UnrecognizedError
import com.foodie.flicks.corenetwork.mapper.ErrorModelMapper
import com.foodie.flicks.daggerscopes.AppScope
import com.foodie.flicks.daggerscopes.SingleIn
import com.foodie.flicks.similaritiesdata.SimilaritiesDetailService
import com.foodie.flicks.similaritiesdata.mapper.toSimilaritiesUiModel
import com.foodie.flicks.similaritiesdata.ui.SimilaritiesUiModel
import com.skydoves.sandwich.map
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
class SimilaritiesRepositoryImpl @Inject constructor(
    private val similaritiesDetailService: SimilaritiesDetailService,
    @Dispatcher(FoodieFlicksDispatcher.IO) private val ioDispatcher: CoroutineDispatcher
) : SimilaritiesRepository {

    override suspend fun getRecipesSimilarities(
        recipeId: Int
    ):
            Flow<Either<ErrorModelException, List<SimilaritiesUiModel>>> =
        flow {
            val response = similaritiesDetailService
                .recipesListSimilarities(
                    id = recipeId
                )

            response.suspendOnSuccess {
                emit(
                    map {
                        this.data.similaritiesResults.toSimilaritiesUiModel()
                    }
                        .right()
                )
            }
                .suspendOnError(ErrorModelMapper) {
                    emit(this.left())
                }
                .suspendOnException {
                    emit(UnrecognizedError.left())
                }
        }
            .flowOn(
                ioDispatcher
            )
}