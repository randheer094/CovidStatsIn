import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.5.21"
    id("com.android.library")
    kotlin("native.cocoapods")
    id("com.squareup.sqldelight")
}

version = "1.0"

kotlin {
    android()

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
        if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
            ::iosArm64
        else
            ::iosX64

    iosTarget("ios") {}

    cocoapods {
        summary = "CovidStatsIN"
        homepage = "CovidStatsIN"
        ios.deploymentTarget = "14.1"
        frameworkName = "MultiPlatformLibrary"
        podfile = project.file("../iosApp/Podfile")
        authors = "Randheer"
    }

    val ktorVersion = "1.6.2"
    val serializationVersion = "1.2.2"
    val coroutineVersion = "1.5.0"
    val sqlDelightVersion = "1.5.0"

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("io.ktor:ktor-client-core:$ktorVersion")
                api("io.ktor:ktor-client-serialization:$ktorVersion")
                api("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.1-native-mt") {
                    version {
                        strictly("1.5.1-native-mt")
                    }
                }
                api("com.squareup.sqldelight:runtime:$sqlDelightVersion")
                api("org.jetbrains.kotlinx:kotlinx-datetime:0.2.1")
                api("com.liftric:kvault:1.6.0")
            }
        }
        val androidMain by getting {
            dependencies {
                api("io.ktor:ktor-client-android:$ktorVersion")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion")
                api("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-ios:$ktorVersion")
                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosTest by getting
    }

    targets.withType(KotlinNativeTarget::class.java).all {
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            isStatic = false
        }
    }
}


android {
    compileSdk = 30
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 30
    }
}

sqldelight {
    database("CovidStats") {
        packageName = "me.randheer.covidstatsin.db"
    }
}
