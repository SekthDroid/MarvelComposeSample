plugins {
    id("com.android.library")
    kotlin("android")
    id("com.google.devtools.ksp")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("com.google.dagger.hilt.android")
    id("app.cash.sqldelight") version "2.0.0-alpha05"
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 21

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "BaseUrl", "\"https://gateway.marvel.com:443/v1/public\"")
        buildConfigField("String", "PublicKey", project.properties["marvelPublicKey"].toString())
        buildConfigField("String", "PrivateKey", project.properties["marvelPrivateKey"].toString())
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    namespace = "com.sekthdroid.marvel.data"
}

sqldelight {
    databases {
        create("AppDatabase") {

        }
    }
}

dependencies {
    implementation(project(":domain"))

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:_")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:_")

    // Ktor
    implementation("io.ktor:ktor-client-core:_")
    implementation("io.ktor:ktor-client-android:_")
    implementation("io.ktor:ktor-client-logging:_")
    implementation("io.ktor:ktor-client-content-negotiation:_")
    implementation("io.ktor:ktor-serialization-kotlinx-json:_")

    // SqlDelight
    implementation("app.cash.sqldelight:android-driver:_")

    // Hilt
    implementation("com.google.dagger:hilt-android:_")
    kapt("com.google.dagger:hilt-compiler:_")

    // Tests
    testImplementation("junit:junit:_")
    testImplementation("androidx.room:room-testing:_")
}
