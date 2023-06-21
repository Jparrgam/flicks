package com.foodie.flicks.corenetwork.dispatcher

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val foodieFlicksDispatcher: FoodieFlicksDispatcher)

enum class FoodieFlicksDispatcher { IO }