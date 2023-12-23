// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    extra.set("targetAndroidSdk", 34)
    extra.set("minAndroidSdk", 26)
    extra.set("jdkVersion", 17)
    extra.set("compilerExtensionVersion", "1.5.6")
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.android) apply false
}
