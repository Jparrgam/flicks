package com.foodie.flicks.di

import android.content.Context
import androidx.activity.ComponentActivity
import com.foodie.flicks.corenetwork.NetworkingModule
import com.foodie.flicks.corenetwork.di.DispatchersModule
import com.foodie.flicks.daggerscopes.AppScope
import com.foodie.flicks.daggerscopes.SingleIn
import com.squareup.anvil.annotations.MergeComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Provider

@MergeComponent(
    scope = AppScope::class,
    modules =
    [
        NetworkingModule::class,
        CircuitModule::class,
        DispatchersModule::class,
        ActivityModule::class
    ]
)
@SingleIn(AppScope::class)
interface AppComponent {
    val activityProviders: Map<Class<out ComponentActivity>, @JvmSuppressWildcards Provider<ComponentActivity>>

    @Component.Factory
    interface Factory {
        fun create(@ApplicationContext @BindsInstance context: Context): AppComponent
    }

    companion object {
        fun create(context: Context): AppComponent = DaggerAppComponent.factory().create(context)
    }
}