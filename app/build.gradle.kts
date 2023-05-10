plugins {
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("com.android.application")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    compileSdk = 33

    defaultConfig {
        applicationId = "com.sekthdroid.compose.marvel"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    namespace = "com.sekthdroid.compose.marvel"
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))

    // Android
    implementation("androidx.navigation:navigation-compose:_")
    implementation("androidx.core:core-ktx:_")
    implementation("androidx.compose.ui:ui:_")
    implementation("androidx.compose.material:material:_")
    implementation("androidx.compose.ui:ui-tooling-preview:_")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:_")
    implementation("androidx.activity:activity-compose:_")
    implementation("io.coil-kt:coil-compose:_")

    // Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:_")
    implementation("com.google.accompanist:accompanist-insets:_")
    implementation("com.google.accompanist:accompanist-insets-ui:_")

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:_")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:_")

    // For future, just wanted to remember it
    implementation("androidx.paging:paging-runtime:_")
    implementation("androidx.paging:paging-compose:_")

    // Hilt
    implementation("com.google.dagger:hilt-android:_")
    kapt("com.google.dagger:hilt-compiler:_")
    implementation("androidx.hilt:hilt-navigation-compose:_")

    // Test
    testImplementation("junit:junit:_")
    androidTestImplementation("androidx.test.ext:junit:_")
    androidTestImplementation("androidx.test.espresso:espresso-core:_")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:_")

    // Debug
    debugImplementation("androidx.compose.ui:ui-tooling:_")
}
