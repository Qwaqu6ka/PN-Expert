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
include(":core:feature-api")
include(":core:presentation")
include(":core:theme")
include(":features:sign-up-api")
include(":features:sign-up-impl")