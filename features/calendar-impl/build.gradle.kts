plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    val targetAndroidSdk: Int by rootProject.extra
    val minAndroidSdk: Int by rootProject.extra
    val jdkVersion: Int by rootProject.extra
    val compilerExtensionVersion: String by rootProject.extra

    namespace = "ru.fefu.calendar_impl"
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

dependencies {

    implementation(libs.navigation.compose)
    implementation(libs.navigation.common)
    implementation(libs.navigation.runtime)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material3)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)

    api(project(":features:calendar-api"))
    api(project(":features:photo-tests-api"))
    api(project(":features:written-test-api"))
    implementation(project(":core:presentation"))
}