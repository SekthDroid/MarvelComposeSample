pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        id("com.android.application") version "8.0.1"
        id("com.android.library") version "8.0.1"
        kotlin("android") version "1.8.21"
        kotlin("plugin.serialization") version "1.8.21"
        kotlin("kapt") version "1.8.21"
        id("com.google.devtools.ksp") version "1.8.21-1.0.11" apply false
        id("com.google.dagger.hilt.android") version "2.44" apply false
        kotlin("jvm") version "1.8.21"
        id("de.fayard.refreshVersions") version "0.51.0"
    }
    resolutionStrategy {
        eachPlugin {

        }
    }
}

plugins {
    id("de.fayard.refreshVersions")
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "MarvelCompose"
include(":app")
include(":domain")
include(":data")
