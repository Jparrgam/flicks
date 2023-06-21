package com.foodie.flicks.di

import androidx.activity.ComponentActivity
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class ActivityKey(val value: KClass<out ComponentActivity>)
