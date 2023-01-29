buildscript {
    // common SDK versions
    val vCompileSdk by extra(33)
    val vMinSdk by extra(24)
    val vTargetSdk by extra(33)

    // common dependency versions
    val vActivity by extra("1.6.1")
    val vAndroidX by extra("1.9.0")
    val vComposeBom by extra("2023.01.00")
    val vComposeCompiler by extra("1.4.0")
    val vEspresso by extra("3.5.1")
    val vJunit by extra("4.13.2")
    val vJunitExt by extra("1.1.5")
    val vKoin by extra("3.3.2")
    val vKoinCompose by extra("3.4.1")
    val vLifecycle by extra("2.6.0-alpha05")
    val vNavigation by extra("2.5.3")
    val vTimber by extra("5.0.1")
}

plugins {
    // Android
    id("com.android.library") version "7.4.0" apply false
    id("com.android.application") version "7.4.0" apply false

    // Kotlin
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
