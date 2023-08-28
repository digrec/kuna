buildscript {
    // common SDK versions
    val vCompileSdk by extra(34)
    val vMinSdk by extra(24)
    val vTargetSdk by extra(34)

    // common dependency versions
    val vActivity by extra("1.7.2")
    val vAndroidX by extra("1.10.1")
    val vComposeBom by extra("2023.08.00")
    val vComposeCompiler by extra("1.5.2")
    val vDesugarJdk by extra("2.0.3")
    val vEspresso by extra("3.5.1")
    val vJunit by extra("4.13.2")
    val vJunitExt by extra("1.1.5")
    val vKoin by extra("3.4.3")
    val vKoinCompose by extra("3.4.6")
    val vLifecycle by extra("2.6.1")
    val vNavigation by extra("2.6.0")
    val vTimber by extra("5.0.1")
}

plugins {
    // Android
    id("com.android.library") version "8.1.1" apply false
    id("com.android.application") version "8.1.1" apply false

    // Kotlin
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
