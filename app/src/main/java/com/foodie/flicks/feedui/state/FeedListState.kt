package com.foodie.flicks.feedui.state

import com.foodie.flicks.feeddata.model.FeedVideoDetailUiModel
import com.foodie.flicks.feedui.event.FeedListEvent
import com.slack.circuit.runtime.CircuitUiState

data class FeedListState(
    val feedList: List<FeedVideoDetailUiModel> = emptyList(),
    val eventSink: (FeedListEvent) -> Unit = {},
) : CircuitUiState