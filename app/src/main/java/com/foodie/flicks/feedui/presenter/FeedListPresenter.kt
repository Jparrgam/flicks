package com.foodie.flicks.feedui.presenter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import com.foodie.flicks.daggerscopes.AppScope
import com.foodie.flicks.feeddata.model.FeedVideoDetailUiModel
import com.foodie.flicks.feeddata.respository.FeedListRepository
import com.foodie.flicks.feedui.event.FeedListEvent
import com.foodie.flicks.feedui.screen.FeedListScreen
import com.foodie.flicks.feedui.state.FeedListState
import com.foodie.flicks.similarities.screen.SimilaritiesScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class FeedListPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    private val feedListRepository: FeedListRepository,
) : Presenter<FeedListState> {
    @Composable override fun present(): FeedListState {
        val feedListState by produceState<List<FeedVideoDetailUiModel>>(
            emptyList(),
            feedListRepository
        ) {
            feedListRepository
                .getFeedList()
                .collect { response ->
                    response.map {
                        value = it
                    }
                }
        }

        return FeedListState(
            feedList = feedListState
        ) { feedEventList ->
            when (feedEventList) {
                is FeedListEvent.SimilaritiesRecipes ->
                    navigator.goTo(
                        SimilaritiesScreen(
                            recipeId = feedEventList.recipeId
                        )
                    )
            }
        }
    }

    @CircuitInject(
        FeedListScreen::class,
        AppScope::class
    )
    @AssistedFactory
    interface Factory {
        fun create(navigator: Navigator): FeedListPresenter
    }
}