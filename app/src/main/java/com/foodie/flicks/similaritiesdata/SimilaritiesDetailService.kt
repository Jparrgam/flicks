package com.foodie.flicks.similaritiesdata

import com.foodie.flicks.daggerscopes.AppScope
import com.foodie.flicks.daggerscopes.ContributesApi
import com.foodie.flicks.similaritiesdata.model.SimilaritiesResultsModelResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

@ContributesApi(AppScope::class)
interface SimilaritiesDetailService {

    @GET("recipes/list-similarities")
    suspend fun recipesListSimilarities(
        @Query("recipe_id") id: Int
    ):
            ApiResponse<SimilaritiesResultsModelResponse>
}