package com.foodie.flicks.similarities.screen

import com.slack.circuit.runtime.Screen
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimilaritiesScreen(
    val recipeId: Int
) : Screen