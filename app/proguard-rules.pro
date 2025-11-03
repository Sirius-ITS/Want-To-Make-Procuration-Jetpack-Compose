# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# ✅ Aggressive optimization flags
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
-allowaccessmodification
-repackageclasses ''
-overloadaggressively
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*

# Preserve line numbers for debugging
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# ✅ More aggressive - remove all attributes except essential ones
-keepattributes Signature,Exceptions,*Annotation*,InnerClasses,EnclosingMethod

# Kotlin - Optimized (less aggressive keep)
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
    static void checkNotNullParameter(java.lang.Object, java.lang.String);
    static void checkNotNullExpressionValue(java.lang.Object, java.lang.String);
    static void checkExpressionValueIsNotNull(java.lang.Object, java.lang.String);
    static void throwUninitializedPropertyAccessException(java.lang.String);
}

# Kotlinx Serialization
-dontnote kotlinx.serialization.AnnotationsKt
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}
-keep,includedescriptorclasses class com.informatique.tawsekmisr.**$$serializer { *; }
-keepclassmembers class com.informatique.tawsekmisr.** {
    *** Companion;
}
-keepclasseswithmembers class com.informatique.tawsekmisr.** {
    kotlinx.serialization.KSerializer serializer(...);
}

# Ktor - Minimal keep rules
-keep class io.ktor.client.** { *; }
-keep class io.ktor.http.** { *; }
-keep class io.ktor.utils.** { *; }
-dontwarn kotlinx.atomicfu.**
-dontwarn io.netty.**
-dontwarn com.typesafe.**
-dontwarn org.slf4j.**

# Ktor Debug Detector - Ignore Java Management classes (not available on Android)
-dontwarn java.lang.management.ManagementFactory
-dontwarn java.lang.management.RuntimeMXBean
-dontwarn javax.management.**

# Hilt - Minimal
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper { *; }
-dontwarn com.google.errorprone.annotations.**

# Jetpack Compose - More aggressive
-dontwarn androidx.compose.**
-keep class androidx.compose.runtime.** { *; }
-keepclassmembers class androidx.compose.** {
    <init>(...);
}

# Google Play Services & Maps - Minimal
-keep class com.google.android.gms.maps.** { *; }
-keep class com.google.android.gms.location.** { *; }
-dontwarn com.google.android.gms.**

# Room - Minimal
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# Data classes and models (adjust package name)
-keep class com.informatique.tawsekmisr.data.model.** { *; }
-keep class com.informatique.tawsekmisr.domain.model.** { *; }

# For native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# For enumeration classes
-keepclassmembers,allowoptimization enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Parcelable
-keepclassmembers class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

# AndroidX Test - Fix for missing concurrent futures
-dontwarn androidx.concurrent.futures.SuspendToFutureAdapter

# ✅ Remove ALL logging in release builds (more aggressive)
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
    public static *** wtf(...);
}

# ✅ Remove print statements
-assumenosideeffects class kotlin.io.ConsoleKt {
    public static *** println(...);
    public static *** print(...);
}

# ✅ Remove printStackTrace calls
-assumenosideeffects class java.lang.Throwable {
    public void printStackTrace();
}

# ✅ Remove toString() calls on final classes (reduces code)
-assumenosideeffects class java.lang.StringBuilder {
    public java.lang.String toString();
}

# ✅ Remove debug/verbose code
-assumenosideeffects class * {
    public void debug(...);
    public void trace(...);
}
