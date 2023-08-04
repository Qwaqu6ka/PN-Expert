pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PN Expert"
include(":app")
include(":features:sign-up")
include(":features:main-menu")
include(":core:theme")
include(":navigation")
include(":core:presentation")
