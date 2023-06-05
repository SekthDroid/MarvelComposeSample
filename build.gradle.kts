import org.gradle.api.tasks.Delete

plugins {
    // Add your plugins here
    id("com.android.application") version "8.0.1" apply false
    id("com.android.library") version "8.0.1" apply false
    kotlin("android") version "1.8.21" apply false
    kotlin("plugin.serialization") version "1.8.21"
    kotlin("kapt") version "1.8.21"
    id("com.google.devtools.ksp") version "1.8.21-1.0.11" apply false
    id("com.google.dagger.hilt.android") version "2.44" apply false
    kotlin("jvm") version "1.8.21"
    kotlin("multiplatform") version "1.8.21" apply false
    //kotlin("native.cocoapods") version "1.8.21" apply false
}

//tasks {
//    val clean by registering(Delete::class) {
//        delete(rootProject.buildDir)
//    }
//}