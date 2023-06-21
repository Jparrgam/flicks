package com.foodie.flicks.feeddata

import com.foodie.flicks.daggerscopes.AppScope
import com.foodie.flicks.daggerscopes.ContributesApi
import com.foodie.flicks.model.RecipesAndFeedListModelResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

@ContributesApi(AppScope::class)
interface FeedListService {

    @GET("feeds/list")
    suspend fun getFeedList(
        @Query("size") size: Int = 10,
        @Query("timezone") timezone: String = "+0500",
        @Query("vegetarian") vegetarian: Boolean = false,
        @Query("from") from: Int = 1
    ):
            ApiResponse<RecipesAndFeedListModelResponse>
}