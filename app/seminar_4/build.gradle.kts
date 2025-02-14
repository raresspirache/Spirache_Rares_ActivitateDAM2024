plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.seminar_4"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.seminar_4"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

configurations.all {
    resolutionStrategy {
        // Force the latest version of annotations
        force("org.jetbrains:annotations:23.0.0")
    }
}

dependencies {
    implementation(libs.appcompat) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.material) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.room.compiler) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.room.runtime) {
        exclude(group = "com.intellij", module = "annotations")
    }
    implementation(libs.firebase.database) {
        exclude(group = "com.intellij", module = "annotations")
    }

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
