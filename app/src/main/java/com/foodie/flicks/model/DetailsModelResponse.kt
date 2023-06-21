package com.foodie.flicks.model


import com.squareup.moshi.Json

data class DetailsModelResponse(
    @Json(name = "approved_at")
    val approvedAt: Int = 0,
    @Json(name = "aspect_ratio")
    val aspectRatio: String = "",
    @Json(name = "canonical_id")
    val canonicalId: String = "",
    @Json(name = "cook_time_minutes")
    val cookTimeMinutes: Int? = 0,
    @Json(name = "country")
    val country: String = "",
    @Json(name = "created_at")
    val createdAt: Int = 0,
    @Json(name = "description")
    val description: String = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "instructions")
    val instructions: List<InstructionsModelResponse> = listOf(),
    @Json(name = "is_shoppable")
    val isShoppable: Boolean = false,
    @Json(name = "name")
    val name: String = "",
    @Json(name = "num_servings")
    val numServings: Int = 0,
    @Json(name = "nutrition_visibility")
    val nutritionVisibility: String = "",
    @Json(name = "original_video_url")
    val originalVideoUrl: String? = "",
    @Json(name = "prep_time_minutes")
    val prepTimeMinutes: Int? = 0,
    @Json(name = "price")
    val price: PriceModelResponse,
    @Json(name = "sections")
    val sections: List<SectionModelResponse> = listOf(),
    @Json(name = "show_id")
    val showId: Int = 0,
    @Json(name = "tags")
    val feeTagsModelResponses: List<TagsModelResponse> = listOf(),
    @Json(name = "thumbnail_alt_text")
    val thumbnailAltText: String = "",
    @Json(name = "thumbnail_url")
    val thumbnailUrl: String = "",
    @Json(name = "tips_and_ratings_enabled")
    val tipsAndRatingsEnabled: Boolean = false,
    @Json(name = "topics")
    val topics: List<TopicModelResponse> = listOf(),
    @Json(name = "total_time_minutes")
    val totalTimeMinutes: Int? = 0,
    @Json(name = "total_time_tier")
    val totalTimeTier: TotalTimeTierModelResponse?,
    @Json(name = "updated_at")
    val updatedAt: Int = 0,
    @Json(name = "user_ratings")
    val userRatings: UserRatingModelResponse,
    @Json(name = "video_url")
    val videoUrl: String? = "",
    @Json(name = "yields")
    val yields: String = ""
)