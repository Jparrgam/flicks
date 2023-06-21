package com.foodie.flicks.feedui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.constraintlayout.compose.ConstraintLayout
import com.foodie.flicks.R
import com.foodie.flicks.daggerscopes.AppScope
import com.foodie.flicks.feedui.event.FeedListEvent
import com.foodie.flicks.feedui.screen.FeedListScreen
import com.foodie.flicks.feedui.state.FeedListState
import com.foodie.flicks.ui.theme.PurpleGrey40
import com.foodie.flicks.widget.VideoPlayerWidget
import com.slack.circuit.codegen.annotations.CircuitInject

@OptIn(ExperimentalAnimationApi::class)
@Composable
@CircuitInject(FeedListScreen::class, AppScope::class)
fun FeedListView(
    state: FeedListState,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(
        initialPage = 0
    )

    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        lowVelocityAnimationSpec = tween(
            easing = LinearEasing,
            durationMillis = 300
        )
    )

    var pauseButtonVisibility by remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier
            .background(
                color = PurpleGrey40
            )
            .systemBarsPadding(),
    ) { paddingValues ->
        VerticalPager(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    paddingValues
                ),
            pageCount = state.feedList.size,
            state = pagerState,
            flingBehavior = fling,
            beyondBoundsPageCount = 1
        ) { feedVideoIndex ->
            val feedVideo = state.feedList[feedVideoIndex]
            ConstraintLayout {
                val (
                    videoPlayerRef,
                    descriptionVideoRef,
                    pauseButtonRef
                )
                        = createRefs()

                VideoPlayerWidget(
                    modifier = Modifier.constrainAs(videoPlayerRef) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                    videoUrl = feedVideo.videoUrl,
                    thumbnailUrl = feedVideo.thumbnailUrl,
                    pagerState = pagerState,
                    pageIndex = feedVideoIndex,
                    onSingleTap = {
                        pauseButtonVisibility = it.isPlaying
                        it.playWhenReady = !it.isPlaying
                    }
                )

                Column(
                    modifier = Modifier
                        .constrainAs(descriptionVideoRef) {
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .padding(
                            start = dimensionResource(
                                id = R.dimen.padding_10dp
                            ),
                            end = dimensionResource(
                                id = R.dimen.padding_10dp
                            ),
                        ),
                ) {
                    Text(
                        modifier = Modifier
                            .padding(
                                start = dimensionResource(
                                    id = R.dimen.padding_10dp
                                ),
                                end = dimensionResource(
                                    id = R.dimen.padding_10dp
                                ),
                            ),
                        text = feedVideo.description,
                        style = MaterialTheme.typography.bodyLarge,
                        overflow = TextOverflow.Clip,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )

                    Row(
                        modifier = Modifier
                            .padding(
                                start = dimensionResource(
                                    id = R.dimen.padding_10dp
                                ),
                                bottom = dimensionResource(
                                    id = R.dimen.padding_20dp
                                ),
                                top = dimensionResource(
                                    id = R.dimen.padding_20dp
                                )
                            )
                            .clickable {
                                state.eventSink(
                                    FeedListEvent.SimilaritiesRecipes(
                                        associatedRecipeId = feedVideo.recipesId
                                    )
                                )
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.detail_recipes_feed
                            ),
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White,
                            textAlign = TextAlign.Start
                        )

                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }

                AnimatedVisibility(
                    visible = pauseButtonVisibility,
                    enter = scaleIn(
                        spring(
                            Spring.DampingRatioMediumBouncy
                        ),
                        initialScale = 1.5f
                    ),
                    exit = scaleOut(
                        tween(150)
                    ),
                    modifier = Modifier.constrainAs(pauseButtonRef) {
                        centerVerticallyTo(parent)
                        centerHorizontallyTo(parent)
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(
                            dimensionResource(
                                id = R.dimen.size_36dp
                            )
                        ),
                        imageVector = Icons.Filled.PlayArrow,
                        tint = Color.White,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}