plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.ktfmt)
}

ktfmt { kotlinLangStyle() }

// Semantic version (updated by Release Please)
val versionMajor = 1 // x-release-please-major
val versionMinor = 3 // x-release-please-minor
val versionPatch = 0 // x-release-please-patch

android {
    namespace = "com.digrec.kuna"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.digrec.kuna"

        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()

        versionCode = versionMajor * 10000 + versionMinor * 100 + versionPatch
        versionName = "$versionMajor.$versionMinor.$versionPatch"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testProguardFiles("proguard-android-test.pro")
        vectorDrawables { useSupportLibrary = true }
    }

    buildTypes {
        debug { versionNameSuffix = "-debug" }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    testBuildType = "release"

    buildFeatures {
        compose = true
        buildConfig = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/versions/9/previous-compilation-data.bin"
        }
    }
}

ksp { arg("room.schemaLocation", "$projectDir/schemas") }

kotlin {
    // Enforces JDK for both Kotlin and Java compilation
    jvmToolchain(jdkVersion = 17)
}

dependencies {

    // JDK Desugar
    coreLibraryDesugaring(libs.android.desugarJdkLibs)

    // AndroidX
    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.activityCompose)
    implementation(libs.androidx.lifecycleRuntimeKtx)
    implementation(libs.androidx.lifecycleRuntimeCompose)
    implementation(libs.androidx.navigationCompose)

    // Compose
    implementation(platform(libs.androidx.composeBom))
    androidTestImplementation(platform(libs.androidx.composeBom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.materialIconsCore)

    // Compose Preview
    implementation(libs.androidx.uiToolingPreview)
    debugImplementation(libs.androidx.uiTooling)

    // Koin DI
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.android)
    implementation(libs.koin.androidxCompose)

    // Room
    implementation(libs.androidx.roomRuntime)
    implementation(libs.androidx.roomKtx)
    annotationProcessor(libs.androidx.roomCompiler)
    ksp(libs.androidx.roomCompiler)

    // Ktor
    implementation(libs.ktor.clientAndroid)
    implementation(libs.ktor.clientContentNegotiation)
    implementation(libs.ktor.clientSerialization)
    implementation(libs.ktor.serializationKotlinxJson)
    implementation(libs.ktor.clientLogging)
    implementation(libs.slf4j.simple)

    // Coil
    implementation(libs.coil.compose)

    // Kotlin Serialization
    implementation(libs.jetbrains.kotlinxSerializationJson)

    // Kotlin Date/Time
    implementation(libs.jetbrains.kotlinxDatetime)

    // Timber
    implementation(libs.jakewharton.timber)

    // Unit Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

    // UI Tests
    androidTestImplementation(libs.androidx.uiTestJunit4)
    debugImplementation(libs.androidx.uiTestManifest)
    androidTestImplementation(libs.androidx.espressoCore)
}

// Custom tasks are required under AGP 9.0+ built-in Kotlin support because the
// ktfmt-gradle plugin fails to automatically detect and format files in 'src/**/*.kt'.
val ktfmtFormatKotlin =
    tasks.register<com.ncorti.ktfmt.gradle.tasks.KtfmtFormatTask>("ktfmtFormatKotlin") {
        description = "Formats Kotlin code using Ktfmt."
        source = project.fileTree(projectDir) { include("src/**/*.kt") }
    }

val ktfmtCheckKotlin =
    tasks.register<com.ncorti.ktfmt.gradle.tasks.KtfmtCheckTask>("ktfmtCheckKotlin") {
        description = "Checks Kotlin code for formatting compliance using Ktfmt."
        source = project.fileTree(projectDir) { include("src/**/*.kt") }
    }

tasks.named("ktfmtFormat") { dependsOn(ktfmtFormatKotlin) }

tasks.named("ktfmtCheck") { dependsOn(ktfmtCheckKotlin) }
