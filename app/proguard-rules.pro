# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# --- Global / General ---
-dontwarn javax.lang.model.element.Modifier
-dontwarn java.lang.management.**
-dontwarn javax.management.**
-dontwarn com.google.errorprone.annotations.**
-dontwarn org.checkerframework.**

# Preserve line numbers and source file names for crash reporting
-keepattributes SourceFile, LineNumberTable

# --- Room ---
# Room uses reflection to instantiate generated _Impl classes.
-keep class * extends androidx.room.RoomDatabase
-keep class * extends androidx.room.Entity
-keep class * extends androidx.room.Dao
-keep class **_Impl { *; }

# --- kotlinx-serialization ---
# Preserve annotations and generated serializers
-keepattributes *Annotation*, InnerClasses, EnclosingMethod, Signature, Exceptions
-keep @kotlinx.serialization.Serializable class * { *; }
-keepclassmembers class ** {
    @kotlinx.serialization.Serializable *;
}
-keepclassmembers class ** {
    @kotlinx.serialization.Serializer *;
}

# --- Koin ---
# Keep ViewModel classes as they are instantiated via reflection
-keep class * extends androidx.lifecycle.ViewModel { *; }

# --- Ktor ---
# Specific rules for Ktor engines and serialization.
-keep class io.ktor.client.engine.android.** { *; }
-keep class io.ktor.client.plugins.contentnegotiation.** { *; }
-keep class io.ktor.client.plugins.logging.** { *; }
-keep class io.ktor.serialization.kotlinx.json.** { *; }

# Narrowed down util keeps to avoid broad warnings
-keep class io.ktor.util.PlatformUtils { *; }
-keep class io.ktor.util.Attributes { *; }
-keep class io.ktor.util.pipeline.** { *; }
-keep class io.ktor.util.collections.** { *; }

-keep class io.ktor.http.cio.** { *; }
-keep class io.ktor.http.content.** { *; }

# --- Timber ---
-keep class timber.log.** { *; }

# --- Testing Support (App APK) ---
# When running instrumentation tests against the release build (testBuildType = "release"),
# the App APK must provide certain classes that the test runner expects to find in
# the app process. Without these, you will get ClassNotFoundException in tests.
-keep class androidx.tracing.** { *; }
-keep class kotlin.LazyKt { *; }
-keep class kotlin.jvm.internal.Intrinsics { *; }
