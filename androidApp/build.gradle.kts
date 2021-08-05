plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":MultiPlatformLibrary"))
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.databinding:viewbinding:7.0.0")

    val lifecycleVersion = "2.3.1"
    implementation("androidx.core:core-ktx:1.6.0")
    implementation("androidx.activity:activity-ktx:1.3.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")

    val composeVersion = "1.0.1"
    implementation("androidx.compose.runtime:runtime:$composeVersion")
}

android {
    compileSdk = 30
    defaultConfig {
        applicationId = "me.randheer.covidstatsin.android"
        minSdk = 21
        targetSdk = 30
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.1"
    }
}