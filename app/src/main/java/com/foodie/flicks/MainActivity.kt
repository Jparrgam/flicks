package com.foodie.flicks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.foodie.flicks.daggerscopes.AppScope
import com.foodie.flicks.di.ActivityKey
import com.foodie.flicks.feedui.screen.FeedListScreen
import com.foodie.flicks.ui.theme.FoodieFlicksTheme
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.CircuitConfig
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.push
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.runtime.Screen
import com.squareup.anvil.annotations.ContributesMultibinding
import javax.inject.Inject

@ContributesMultibinding(AppScope::class, boundType = ComponentActivity::class)
@ActivityKey(MainActivity::class)
class MainActivity @Inject constructor(
    private val circuitConfig: CircuitConfig
) : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodieFlicksTheme {
                val backStack: MutableList<Screen> = mutableListOf(FeedListScreen)

                Surface(color = MaterialTheme.colorScheme.background) {
                    val backstack =
                        rememberSaveableBackStack { backStack.forEach { screen -> push(screen) } }
                    val navigator =
                        rememberCircuitNavigator(backstack)

                    CircuitCompositionLocals(circuitConfig = circuitConfig) {
                        NavigableCircuitContent(
                            navigator = navigator,
                            backstack = backstack
                        )
                    }
                }
            }
        }
    }
}