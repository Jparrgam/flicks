package com.foodie.flicks.corenetwork.di

import com.foodie.flicks.corenetwork.dispatcher.Dispatcher
import com.foodie.flicks.corenetwork.dispatcher.FoodieFlicksDispatcher
import com.foodie.flicks.daggerscopes.AppScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@ContributesTo(AppScope::class)
object DispatchersModule {
    @Provides
    @Dispatcher(FoodieFlicksDispatcher.IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}