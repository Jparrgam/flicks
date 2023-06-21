package com.foodie.flicks.daggerscopes

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
annotation class ContributesApi(val scope: KClass<*>)
