buildscript {
    extra.set("targetAndroidSdk", 34)
    extra.set("minAndroidSdk", 26)
    extra.set("jdkVersion", 17)
    extra.set("compilerExtensionVersion", "1.5.14")
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
}
