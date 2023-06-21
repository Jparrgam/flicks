package com.foodie.flicks.similarities.event

import com.slack.circuit.runtime.CircuitUiEvent

sealed interface SimilaritiesEvent : CircuitUiEvent {
    object Back : SimilaritiesEvent
}
