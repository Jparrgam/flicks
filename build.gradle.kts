import org.jetbrains.kotlin.gradle.internal.KaptGenerateStubsTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.anvilPlugin) apply true
}
subprojects {
    // The app module applies Kapt and Anvil should not generated Dagger factories to avoid duplicate classes.
    if (project.name != "app") {
        project.pluginManager.withPlugin("com.squareup.anvil") {
            anvil {
                generateDaggerFactories.set(true)
            }
        }
    }

    tasks
        .withType<KotlinCompile>()
        .matching { it !is KaptGenerateStubsTask }
        .configureEach {
            compilerOptions {
                freeCompilerArgs.addAll(
                    "-opt-in=androidx.compose.material.ExperimentalMaterialApi",
                    "-opt-in=androidx.compose.foundation.layout.ExperimentalLayoutApi",
                    "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi",
                    "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api",
                    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                    "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
                )
            }
        }
}