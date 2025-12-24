@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android") // Hilt plugin
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.luxuryvault.android"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.luxuryvault.android"
        minSdk = 29
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField(
            "String",
            "PEXELS_API_KEY",
            "\"${project.findProperty("PEXELS_API_KEY") ?: ""}\""
        )
        buildConfigField(
            "String",
            "BASE_URL",
            "\"https://pexels.com/api/\""
        )
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        target {
            compilerOptions {
                optIn.add("kotlin.RequiresOptIn")
            }
        }
        jvmToolchain(17)
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)



    testImplementation(libs.hamcrest)
    //testImplementation(libs.androidx.core.testing) // latest stable
    testImplementation(libs.robolectric)

    testImplementation(libs.truth)
    testImplementation(libs.mockito.core)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Navigation for Compose
    implementation(libs.androidx.navigation.compose)

    // Hilt + Navigation Compose integration
    implementation(libs.androidx.hilt.navigation.compose)

    // Testing Hilt (useful for UI tests)
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.hilt.compiler)

    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.core) // latest stable
    implementation(libs.kotlinx.coroutines.android) // Android-specific dispatcher


    // Lifecycle / ViewModel / KTX
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx.v262)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Room DB
    implementation(libs.room.runtime)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.androidx.core.testing.v220)

    ksp(libs.androidx.room.compiler.v250)
    testImplementation(libs.androidx.room.testing)
    testImplementation(libs.kotlinx.coroutines.test)

    // Retrofit
    implementation(libs.retrofit)
    // Retrofit + Gson converter
    implementation(libs.converter.gson)
    // OkHttp logging (optional but recommended)
    implementation(libs.logging.interceptor)

    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.coil.compose)
}