plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
}

val vCompileSdk: Int by rootProject.extra
val vMinSdk: Int by rootProject.extra
val vTargetSdk: Int by rootProject.extra
val vComposeCompiler: String by rootProject.extra

// Semantic version (updated by Release Please)
val versionMajor = 1    // x-release-please-major
val versionMinor = 1    // x-release-please-minor
val versionPatch = 0    // x-release-please-patch

android {
    namespace = "com.digrec.kuna"
    compileSdk = vCompileSdk

    defaultConfig {
        applicationId = "com.digrec.kuna"

        minSdk = vMinSdk
        targetSdk = vTargetSdk

        versionCode = versionMajor * 10000 + versionMinor * 100 + versionPatch
        versionName = "$versionMajor.$versionMinor.$versionPatch"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    sourceSets {
        named("main") { java.srcDir("src/main/kotlin") }
        named("test") { java.srcDir("src/test/kotlin") }
        named("androidTest") { java.srcDir("src/androidTest/kotlin") }
    }
    buildTypes {
        debug {
            versionNameSuffix = "-debug"
        }
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    composeOptions {
        kotlinCompilerExtensionVersion = vComposeCompiler
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

val vActivity: String by rootProject.extra
val vAndroidX: String by rootProject.extra
val vComposeBom: String by rootProject.extra
val vDesugarJdk: String by rootProject.extra
val vEspresso: String by rootProject.extra
val vJunit: String by rootProject.extra
val vJunitExt: String by rootProject.extra
val vKoin: String by rootProject.extra
val vKoinCompose: String by rootProject.extra
val vKotlinSerialization: String by rootProject.extra
val vKotlinxDateTime: String by rootProject.extra
val vLifecycle: String by rootProject.extra
val vNavigation: String by rootProject.extra
val vTimber: String by rootProject.extra

dependencies {

    // JDK Desugar
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:$vDesugarJdk")

    // AndroidX
    implementation("androidx.core:core-ktx:$vAndroidX")
    implementation("androidx.activity:activity-compose:$vActivity")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$vLifecycle")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$vLifecycle")
    implementation("androidx.navigation:navigation-compose:$vNavigation")

    // Compose
    val composeBom = platform("androidx.compose:compose-bom:$vComposeBom")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")

    // Compose Preview
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Koin DI
    implementation("io.insert-koin:koin-android:$vKoin")
    implementation("io.insert-koin:koin-androidx-compose:$vKoinCompose")

    // Kotlin Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$vKotlinSerialization")

    // Kotlin Date/Time
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:$vKotlinxDateTime")

    // Timber
    implementation("com.jakewharton.timber:timber:$vTimber")

    // Unit Tests
    testImplementation("junit:junit:$vJunit")
    androidTestImplementation("androidx.test.ext:junit:$vJunitExt")

    // UI Tests
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    androidTestImplementation("androidx.test.espresso:espresso-core:$vEspresso")
}
