package com.foodie.flicks.model

import com.squareup.moshi.Json

data class InstructionsModelResponse(
    @Json(name = "appliance")
    val appliance: Any? = null,
    @Json(name = "display_text")
    val displayText: String = "",
    @Json(name = "end_time")
    val endTime: Int = 0,
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "position")
    val position: Int = 0,
    @Json(name = "start_time")
    val startTime: Int = 0
)