// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val targetAndroidSdk by extra(33)
    val minAndroidSdk by extra(26)
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
}
