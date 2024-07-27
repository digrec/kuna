buildscript {
    // common SDK versions
    val vCompileSdk by extra(34)
    val vMinSdk by extra(24)
    val vTargetSdk by extra(34)

    // common dependency versions
    val vActivity by extra("1.9.1")
    val vAndroidX by extra("1.13.1")
    val vCoil by extra("2.7.0")
    val vComposeBom by extra("2024.06.00")
    val vDesugarJdk by extra("2.0.4")
    val vEspresso by extra("3.6.1")
    val vJunit by extra("4.13.2")
    val vJunitExt by extra("1.2.1")
    val vKoin by extra("3.5.6")
    val vKoinCompose by extra("3.5.6")
    val vKotlinSerialization by extra("1.7.1")
    val vKotlinxDateTime by extra("0.6.0")
    val vKtor by extra("2.3.12")
    val vLifecycle by extra("2.8.4")
    val vNavigation by extra("2.7.7")
    val vRoom by extra("2.6.1")
    val vSlf4j by extra("2.0.13")
    val vTimber by extra("5.0.1")
}

plugins {
    // Android
    id("com.android.library") version "8.5.1" apply false
    id("com.android.application") version "8.5.1" apply false

    // Kotlin
    id("org.jetbrains.kotlin.android") version "2.0.0" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0" apply false
    id("com.google.devtools.ksp") version "2.0.0-1.0.23" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
