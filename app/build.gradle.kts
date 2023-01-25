plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

val vCompileSdk: Int by rootProject.extra
val vMinSdk: Int by rootProject.extra
val vTargetSdk: Int by rootProject.extra
val vComposeCompiler: String by rootProject.extra

android {
    namespace = "com.digrec.kuna"
    compileSdk = vCompileSdk

    defaultConfig {
        applicationId = "com.digrec.kuna"

        minSdk = vMinSdk
        targetSdk = vTargetSdk

        versionCode = 1
        versionName = "0.1"

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
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    composeOptions {
        kotlinCompilerExtensionVersion = vComposeCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

val vActivity: String by rootProject.extra
val vAndroidX: String by rootProject.extra
val vComposeBom: String by rootProject.extra
val vEspresso: String by rootProject.extra
val vJunit: String by rootProject.extra
val vJunitExt: String by rootProject.extra
val vKoin: String by rootProject.extra
val vKoinCompose: String by rootProject.extra
val vLifecycle: String by rootProject.extra
val vNavigation: String by rootProject.extra
val vTimber: String by rootProject.extra

dependencies {

    // AndroidX
    implementation("androidx.core:core-ktx:$vAndroidX")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$vLifecycle")
    implementation("androidx.activity:activity-compose:$vActivity")
    implementation("androidx.navigation:navigation-compose:$vNavigation")

    // Compose
    val composeBom = platform("androidx.compose:compose-bom:$vComposeBom")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-alpha04")

    // Compose Preview
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Koin DI
    implementation("io.insert-koin:koin-android:$vKoin")
    implementation("io.insert-koin:koin-androidx-compose:$vKoinCompose")

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
