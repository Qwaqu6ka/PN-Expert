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
include(":data")
include(":core:feature-api")
include(":core:presentation")
include(":core:theme")
include(":features:main-api")
include(":features:main-impl")
include(":features:photo-tests-api")
include(":features:photo-tests-impl")
include(":features:sign-up-api")
include(":features:sign-up-impl")
include(":features:video-tests-api")
include(":features:video-tests-impl")
include(":features:written-test-api")
include(":features:written-test-impl")
include(":core:common")

