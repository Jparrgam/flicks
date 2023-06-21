package com.foodie.flicks.similarities

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.foodie.flicks.daggerscopes.AppScope
import com.foodie.flicks.similarities.event.SimilaritiesEvent
import com.foodie.flicks.similarities.screen.SimilaritiesScreen
import com.foodie.flicks.similarities.state.SimilaritiesState
import com.skydoves.whatif.whatIf
import com.slack.circuit.codegen.annotations.CircuitInject
import com.valentinilk.shimmer.shimmer

@Composable
@CircuitInject(SimilaritiesScreen::class, AppScope::class)
fun SimilaritiesView(
    similaritiesState: SimilaritiesState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(
                20.dp
            ),
        topBar = {
            Icon(
                modifier = Modifier
                    .clickable {
                        similaritiesState.eventSink(SimilaritiesEvent.Back)
                    },
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .whatIf(similaritiesState.isLoading) {
                    shimmer()
                },
        ) {
            items(items = similaritiesState.similaritiesList) { similaritiesUiModel ->
                Text(
                    modifier = Modifier.padding(
                        20.dp
                    ),
                    text = similaritiesUiModel.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}