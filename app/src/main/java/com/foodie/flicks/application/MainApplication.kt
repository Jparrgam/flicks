package com.foodie.flicks.application

import android.app.Application
import com.foodie.flicks.di.AppComponent

class MainApplication : Application() {

    private val appComponent by lazy { AppComponent.create(this) }

    fun appComponent() = appComponent
}