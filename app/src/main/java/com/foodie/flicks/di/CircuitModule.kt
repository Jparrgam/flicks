package com.foodie.flicks.di

import com.foodie.flicks.daggerscopes.AppScope
import com.slack.circuit.foundation.CircuitConfig
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.ui.Ui
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import dagger.multibindings.Multibinds

@ContributesTo(AppScope::class)
@Module
interface CircuitModule {
    @Multibinds fun presenterFactories(): Set<Presenter.Factory>

    @Multibinds fun viewFactories(): Set<Ui.Factory>

    companion object {
        @Provides
        fun provideCircuit(
            presenterFactories: @JvmSuppressWildcards Set<Presenter.Factory>,
            uiFactories: @JvmSuppressWildcards Set<Ui.Factory>,
        ): CircuitConfig =
            CircuitConfig.Builder()
                .addPresenterFactories(presenterFactories)
                .addUiFactories(uiFactories)
                .build()
    }
}
