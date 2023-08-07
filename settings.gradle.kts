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
include(":features:main-menu")
include(":features:sign-up")
include(":navigation")
include(":core:feature-api")
include(":core:presentation")
include(":core:theme")
