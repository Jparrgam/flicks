@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.anvilPlugin)
    alias(libs.plugins.ksp)
    kotlin("kapt")
    kotlin("plugin.parcelize")
}

android {
    namespace = "com.foodie.flicks"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.foodie.flicks"
        minSdk = 28
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = false
        }
    }

    buildTypes.forEach {
        it.buildConfigField(
            "String",
            "XRapidApiKey",
            "\"43abd8e00cmsh4148202ef68d4bap12c34fjsne542eedb1469\""
        )

        it.buildConfigField(
            "String",
            "XRapidApiHost",
            "\"tasty.p.rapidapi.com\""
        )

        it.buildConfigField(
            "String",
            "BaseUrl",
            "\"https://tasty.p.rapidapi.com/\""
        )
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(11))
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // KTX extensions for Android APIs.
    implementation(libs.core.ktx)

    // KTX extensions for lifecycle handling.
    implementation(libs.lifecycle.runtime.ktx)

    // compose support in activities.
    implementation(libs.activity.compose)

    // jetpack Compose UI and related tools.
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)

    // material 3 for Jetpack Compose.
    implementation(libs.material3)
    implementation(libs.appcompat)

    // jUnit for unit tests.
    testImplementation(libs.junit)

    // testing tools for Android and Compose.
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ui.test.junit4)

    // bill of Materials (BOM) for Compose, version management.
    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))

    // debugging tools for Jetpack Compose.
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)

    // circuit framework
    api(libs.circuit.runtime)
    ksp(libs.circuit.codegen)
    implementation(libs.circuit.codegen.annotations)

    // dagger
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)
    api(projects.daggerscopes)
    anvil(projects.anvilcodegen)

    // kotlin coroutine
    implementation(libs.kotlin.coroutines.core)

    // retrofit
    implementation(libs.retrofit.runtime)
    implementation(libs.retrofit.logging.interceptor)
    implementation(libs.sandwich)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.moshi.kotlin)

    // dagger
    implementation(libs.dagger)
    implementation(projects.daggerscopes)

    // arrow
    implementation(libs.arrow.core)

    // exoplayer
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.exoplayer.dash)
    implementation(libs.androidx.media3.ui)

    // constraint layout
    implementation(libs.androidx.constraintlayout.compose)

    // shimmer
    implementation(libs.compose.shimmer)

    // what if
    implementation(libs.whatif)

    //coil
    implementation(libs.coil.compose)
}