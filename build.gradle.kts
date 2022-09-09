buildscript {
    // common SDK versions
    val vCompileSdk by extra(33)
    val vMinSdk by extra(24)
    val vTargetSdk by extra(33)

    // common dependency versions
    val vAndroidX by extra("1.8.0")
    val vCompose by extra("1.3.0-beta02")
    val vComposeCompiler by extra("1.3.1")
    val vMaterial3 by extra("1.0.0-beta02")
    val vLifecycle by extra("2.5.1")
    val vActivity by extra("1.5.1")
    val vEspresso by extra("3.4.0")
    val vJunit by extra("4.13.2")
    val vJunitExt by extra("1.1.3")
}

plugins {
    // Android
    id("com.android.library") version "7.2.2" apply false
    id("com.android.application") version "7.2.2" apply false

    // Kotlin
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
