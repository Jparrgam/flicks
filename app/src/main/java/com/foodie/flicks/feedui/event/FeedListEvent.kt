package com.foodie.flicks.feedui.event

import com.slack.circuit.runtime.CircuitUiEvent

sealed interface FeedListEvent : CircuitUiEvent {
    data class SimilaritiesRecipes(
        val associatedRecipeId: String
    ) : FeedListEvent {
        val recipeId: Int
            get() = associatedRecipeId.split(":").last().toInt()
    }
}