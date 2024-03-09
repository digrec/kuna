buildscript {
    // common SDK versions
    val vCompileSdk by extra(34)
    val vMinSdk by extra(24)
    val vTargetSdk by extra(34)

    // common dependency versions
    val vActivity by extra("1.8.2")
    val vAndroidX by extra("1.12.0")
    val vCoil by extra("2.6.0")
    val vComposeBom by extra("2024.02.02")
    val vComposeCompiler by extra("1.5.8")
    val vDesugarJdk by extra("2.0.4")
    val vEspresso by extra("3.5.1")
    val vJunit by extra("4.13.2")
    val vJunitExt by extra("1.1.5")
    val vKoin by extra("3.5.3")
    val vKoinCompose by extra("3.5.3")
    val vKotlinSerialization by extra("1.6.2")
    val vKotlinxDateTime by extra("0.5.0")
    val vKtor by extra("2.3.7")
    val vLifecycle by extra("2.6.2")
    val vNavigation by extra("2.7.6")
    val vSlf4j by extra("2.0.10")
    val vTimber by extra("5.0.1")
}

plugins {
    // Android
    id("com.android.library") version "8.3.0" apply false
    id("com.android.application") version "8.3.0" apply false

    // Kotlin
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.22" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}
