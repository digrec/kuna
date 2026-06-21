# Rules for instrumentation tests APK.
# These rules ensure that testing frameworks and test classes are not stripped.

-dontwarn javax.lang.model.element.Modifier
-dontwarn java.lang.management.**
-dontwarn javax.management.**
-dontwarn com.google.errorprone.annotations.**
-dontwarn org.checkerframework.**
-dontwarn org.junit.**

# Keep all test classes
-keep class com.digrec.kuna.**Test { *; }

# Keep common Kotlin internal classes used by tests
-keep class kotlin.jvm.internal.Intrinsics { *; }
-keep class kotlin.LazyKt { *; }

# --- Testing Frameworks ---
-keep class org.junit.** { *; }
-keep class androidx.test.** { *; }
-keep class androidx.tracing.** { *; }

# --- Kotlin for Tests ---
# Keep only what's needed for metadata and lazy initialization in tests
-keep class kotlin.Metadata { *; }
-keep class kotlin.reflect.** { *; }

# --- Allow communication between test and app APKs ---
-keep class com.digrec.kuna.** { *; }
