buildscript {
    // common SDK versions
    val vCompileSdk by extra(34)
    val vMinSdk by extra(24)
    val vTargetSdk by extra(34)

    // common dependency versions
    val vActivity by extra("1.8.1")
    val vAndroidX by extra("1.12.0")
    val vComposeBom by extra("2023.10.01")
    val vCoil by extra("2.5.0")
    val vComposeCompiler by extra("1.5.2")
    val vDesugarJdk by extra("2.0.4")
    val vEspresso by extra("3.5.1")
    val vJunit by extra("4.13.2")
    val vJunitExt by extra("1.1.5")
    val vKoin by extra("3.5.0")
    val vKoinCompose by extra("3.5.0")
    val vKotlinSerialization by extra("1.6.1")
    val vKotlinxDateTime by extra("0.4.1")
    val vKtor by extra("2.3.5")
    val vLifecycle by extra("2.6.2")
    val vNavigation by extra("2.7.4")
    val vSlf4j by extra("2.0.9")
    val vTimber by extra("5.0.1")
}

plugins {
    // Android
    id("com.android.library") version "8.1.4" apply false
    id("com.android.application") version "8.1.4" apply false

    // Kotlin
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
