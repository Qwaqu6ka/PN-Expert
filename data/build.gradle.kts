@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
}

android {
    val targetAndroidSdk: Int by rootProject.extra
    val minAndroidSdk: Int by rootProject.extra
    val jdkVersion: Int by rootProject.extra

    namespace = "ru.fefu.data"
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
}

dependencies {
    // camerax
    implementation(libs.camerax.camera)
    implementation(libs.camerax.lifecycle)
    implementation(libs.camerax.mlkit)
    implementation(libs.camerax.video)
    implementation(libs.camerax.view)

    implementation(libs.datastore.preferences)

    implementation(libs.guava)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // mlkit
    implementation(libs.mlkit.baracode)
    implementation(libs.mlkit.common)

    implementation(libs.retrofit)
    
    implementation(libs.room)
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)

    implementation(project(":core:camera"))
    implementation(project(":core:common"))
    implementation(project(":core:presentation"))
}