import com.android.build.gradle.internal.utils.isKotlinKaptPluginApplied
//@Suppress("DSL_SCOPE_VIOLATION")

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    //id("org.jetbrains.kotlin.kapt") version "2.0.0-Beta4"
    //alias(libs.plugins.kotlin.jvm)
    //alias(libs.plugins.org.jetbrains.kotlin.kapt)
    //alias(libs.plugins.jetbrainsKotlinKapt)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
}
android {
    namespace = "com.example.narodi"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.narodi"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //val room_version = "2.6.1"

    //implementation("androidx.room:room-runtime:$room_version")
    //kapt("androidx.room:room-compiler:$room_version")
    // optional - Kotlin Extensions and Coroutines support for Room

    //implementation("androidx.room:room-ktx:$room_version")
    implementation (libs.roomRuntime)
    kapt (libs.roomCompiler)
    implementation(libs.roomLivedata)
    implementation(libs.roomKtx)
}