plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
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

    // mlkit
    implementation(libs.mlkit.baracode)
    implementation(libs.mlkit.common)
    
    implementation(libs.room)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    implementation(project(":core:camera"))
    implementation(project(":core:common"))
    implementation(project(":core:presentation"))
}