plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
}

android {
    val targetAndroidSdk: Int by rootProject.extra
    val minAndroidSdk: Int by rootProject.extra
    val jdkVersion: Int by rootProject.extra
    val compilerExtensionVersion: String by rootProject.extra

    namespace = "ru.fefu.pnexpert"
    compileSdk = targetAndroidSdk

    defaultConfig {
        val major = 1
        val minor = 0 // max 99
        val patch = 0 // max 99

        applicationId = "ru.fefu.pnexpert"
        minSdk = minAndroidSdk
        targetSdk = targetAndroidSdk
        versionCode = (major * 10000) + (minor * 100) + patch
        versionName = "$major.$minor.$patch"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicators)

    implementation(libs.core.splashscreen)

    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    implementation(project(":features:calendar-api"))
    kapt(libs.hilt.compiler)

    implementation(libs.retrofit)

    implementation(libs.navigation.compose)

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime)
    implementation(libs.activity.compose)
    implementation(platform(libs.kotlin.bom))
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)

    implementation(project(":core:presentation"))
    implementation(project(":data"))
    implementation(project(":features:main-impl"))
    implementation(project(":features:sign-up-impl"))
    implementation(project(":features:written-test-impl"))
    implementation(project(":features:photo-tests-impl"))
    implementation(project(":features:calendar-impl"))
}