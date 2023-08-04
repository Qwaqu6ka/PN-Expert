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

    namespace = "ru.fefu.navigation"
    compileSdk = targetAndroidSdk

    defaultConfig {
        minSdk = minAndroidSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    kotlin {
        jvmToolchain(jdkVersion)
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = compilerExtensionVersion
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
}

dependencies {

    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)
}