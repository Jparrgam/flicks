@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.org.jetbrains.kotlin.jvm)
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    
    toolchain.languageVersion.set(JavaLanguageVersion.of(11))
}

dependencies {
    // anvil
    api(libs.anvil.compiler.api)
    implementation(libs.anvil.compiler.utils)

    // dagger scopes
    api(project(":daggerscopes"))

    // kotlin poet
    implementation(libs.kotlinpoet)

    // dagger
    implementation(libs.dagger)

    // retrofit
    implementation(libs.retrofit.runtime)

    // auto service
    compileOnly(libs.auto.service.annotations)
    kapt(libs.auto.service)
}