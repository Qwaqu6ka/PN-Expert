@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    val targetAndroidSdk: Int by rootProject.extra
    val minAndroidSdk: Int by rootProject.extra
    val jdkVersion: Int by rootProject.extra
    val compilerExtensionVersion: String by rootProject.extra

    namespace = "ru.fefu.video_tests_impl"
    compileSdk = targetAndroidSdk

    defaultConfig {
        minSdk = minAndroidSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    kotlin {
        jvmToolchain(jdkVersion)
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
    composeOptions {
        kotlinCompilerExtensionVersion = compilerExtensionVersion
    }
}

private val camerax_version = "1.3.0"

dependencies {
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)

    // camera
//    implementation(libs.camera.camera2)
//    implementation(libs.camera.video)
//    implementation(libs.camera.mlkit)
//    implementation(libs.camera.lifecycle)
//    implementation(libs.camera.view)

    // tmp
    implementation("com.google.mlkit:pose-detection:18.0.0-beta3")
    implementation("com.google.mlkit:pose-detection-accurate:18.0.0-beta3")
    implementation("androidx.camera:camera-core:1.3.0")
    implementation("com.google.android.odml:image:1.0.0-beta1")
    implementation("com.google.mlkit:camera:16.0.0-beta3")

    implementation("androidx.camera:camera-core:${camerax_version}")
    implementation("androidx.camera:camera-camera2:${camerax_version}")
    implementation ("androidx.camera:camera-lifecycle:${camerax_version}")
    implementation("androidx.camera:camera-video:${camerax_version}")
    implementation ("androidx.camera:camera-view:${camerax_version}")
    implementation ("androidx.camera:camera-extensions:${camerax_version}")
    implementation("androidx.camera:camera-mlkit-vision:1.4.0-alpha02")

    // ai for video
//    implementation(libs.mlkit.posedetection.accurate)

    implementation(libs.accompanist.permissions)

    implementation(project(":core:presentation"))
    api(project(":features:video-tests-api"))
}