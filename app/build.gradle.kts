import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.informatique.tawsekmisr"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.informatique.tawsekmisr"
        minSdk = 26
        targetSdk = 36
        versionCode = 35
        versionName = "1.0"

        testInstrumentationRunner = "com.informatique.tawsekmisr.HiltTestRunner"

        // ✅ Enable vector drawable support to reduce image sizes
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        debug {
            buildConfigField("String", "API_KEY", "\"51563d451c6f724bd8ab8b996d791403\"")
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = true  // ✅ Enable R8 code shrinking
            isShrinkResources = true  // ✅ Remove unused resources
            buildConfigField("String", "API_KEY", "\"51563d451c6f724bd8ab8b996d791403\"")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // ✅ More aggressive optimizations
            ndk {
                debugSymbolLevel = "NONE"  // Remove debug symbols from native libraries
            }

            // ✅ Enable R8 full mode optimizations
            isDebuggable = false
            isJniDebuggable = false
            isPseudoLocalesEnabled = false
            isCrunchPngs = true  // Compress PNG files

            // ✅ Optimize resources
            @Suppress("UnstableApiUsage")
            optimization {
                keepRules {
                }
            }
        }
    }

    // ✅ Split APK by ABI to reduce size (each APK will only contain native libs for specific architecture)
    splits {
        abi {
            isEnable = true
            reset()
            include("armeabi-v7a", "arm64-v8a")
            isUniversalApk = false  // Set to true if you need one APK with all ABIs
        }
    }

    // ✅ Packaging options to exclude unnecessary files
    packaging {
        resources {
            excludes += setOf(
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/license.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt",
                "META-INF/notice.txt",
                "META-INF/ASL2.0",
                "META-INF/*.kotlin_module",
                "META-INF/INDEX.LIST",
                "META-INF/io.netty.versions.properties",
                "META-INF/*.version",
                "DebugProbesKt.bin",
                "kotlin-tooling-metadata.json",
                "kotlin/**",
                "okhttp3/**"
            )
        }
        jniLibs {
            useLegacyPackaging = false  // Use compressed native libraries
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
        isCoreLibraryDesugaringEnabled = true
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    // ✅ Optimize Kotlin compilation
    kotlinOptions {
        freeCompilerArgs += listOf(
            "-Xno-param-assertions",
            "-Xno-call-assertions",
            "-Xno-receiver-assertions"
        )
    }
}

// ✅ Exclude unused dependencies at compile time
configurations.all {
    exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk7")
    exclude(group = "androidx.lifecycle", module = "lifecycle-viewmodel-ktx")
}

dependencies {
    // --- Compose BOM ---
    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))

    // --- Core ---
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    coreLibraryDesugaring(libs.desugar.jdk.libs)

    // --- Compose UI ---
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.material3.window)
    implementation(libs.compose.material.icons)
    implementation(libs.activity.compose)
    implementation(libs.coil.compose)

    // --- Lifecycle (removed duplicate viewmodel-ktx) ---
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.runtime.compose)

    // --- Coroutines ---
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // --- Serialization & Ktor ---
    implementation(libs.serialization.json)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.serialization.kotlinx.json)

    // --- Hilt ---
    implementation(libs.hilt.android)
    add("ksp", libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    add("ksp", libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.work)

    // --- Room ---
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    add("ksp", libs.room.compiler)

    // --- WorkManager ---
    implementation(libs.work.runtime)

    // --- DataStore ---
    implementation(libs.datastore.preferences)

    // --- Splash Screen ---
    implementation(libs.core.splashscreen)

    // --- Navigation Compose (only) ---
    implementation(libs.navigation.compose)

    // --- Google Play Services Location ---
    implementation(libs.play.services.location)

    // --- Google Maps ---
    implementation(libs.maps.compose)
    implementation(libs.play.services.maps)

    // --- Root Detection ---
    implementation(libs.rootbeer.lib)

    //Version Checker
    implementation(libs.jversionchecker)
    implementation(libs.app.update)

    // --- Tests ---
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    testImplementation(libs.mockito.core)
    testImplementation(libs.core.testing)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.turbine)

    // --- Hilt Testing ---
    testImplementation(libs.hilt.android)
    add("kspTest", libs.hilt.compiler)
    androidTestImplementation(libs.hilt.android)
    add("kspAndroidTest", libs.hilt.compiler)
}
