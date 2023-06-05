pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

plugins {
    id("de.fayard.refreshVersions") version "0.51.0"
}

dependencyResolutionManagement {
    //repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "MarvelCompose"
include(":app")
include(":data")
include(":shared-domain")
