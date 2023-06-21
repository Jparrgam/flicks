package com.foodie.flicks.similarities.state

import com.foodie.flicks.similarities.event.SimilaritiesEvent
import com.foodie.flicks.similaritiesdata.ui.SimilaritiesUiModel
import com.slack.circuit.runtime.CircuitUiState

data class SimilaritiesState(
    val similaritiesList: List<SimilaritiesUiModel> = emptyList(),
    val isLoading: Boolean,
    val viewEmptyState: Boolean,
    val eventSink: (SimilaritiesEvent) -> Unit = {},
) : CircuitUiState