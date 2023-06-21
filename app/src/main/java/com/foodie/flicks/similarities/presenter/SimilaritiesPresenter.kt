package com.foodie.flicks.similarities.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.foodie.flicks.daggerscopes.AppScope
import com.foodie.flicks.similarities.event.SimilaritiesEvent
import com.foodie.flicks.similarities.screen.SimilaritiesScreen
import com.foodie.flicks.similarities.state.SimilaritiesState
import com.foodie.flicks.similaritiesdata.respository.SimilaritiesRepository
import com.foodie.flicks.similaritiesdata.ui.SimilaritiesUiModel
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion

class SimilaritiesPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    @Assisted private val screen: SimilaritiesScreen,
    private val similaritiesRepository: SimilaritiesRepository
) : Presenter<SimilaritiesState> {
    @Composable override fun present(): SimilaritiesState {
        var loadingSimilarities by remember { mutableStateOf(true) }
        var viewEmptyState by remember { mutableStateOf(false) }

        val similaritiesState by produceState<List<SimilaritiesUiModel>>(
            emptyList()
        ) {
            similaritiesRepository.getRecipesSimilarities(
                recipeId = screen.recipeId
            )
                .onCompletion { loadingSimilarities = false }
                .catch { viewEmptyState = true }
                .collect { result ->
                    result.map { similarities ->
                        value = similarities
                    }
                }
        }

        return SimilaritiesState(
            isLoading = loadingSimilarities,
            viewEmptyState = viewEmptyState,
            similaritiesList = similaritiesState
        ) { similaritiesEvent ->
            if (similaritiesEvent is SimilaritiesEvent.Back) {
                navigator.pop()
            }
        }
    }

    @CircuitInject(
        SimilaritiesScreen::class,
        AppScope::class
    )
    @AssistedFactory
    interface Factory {
        fun create(
            navigator: Navigator,
            screen: SimilaritiesScreen,
        ): SimilaritiesPresenter
    }
}