plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.daggerHiltAndroid)
    kotlin("kapt")
    kotlin("plugin.serialization") version "1.4.21"
    idea


}

android {
    namespace = "com.asteriatech.mobile"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.asteriatech.mobile"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    //Dagger-Hilt
    implementation(libs.daggerHiltAndroid)
    kapt(libs.daggerHiltCompiler)

    //Retrofit
    implementation(libs.retrofit)

    // Retrofit with Kotlin serialization Converter
    implementation(libs.retrofitKotlinSerializationConverter)
    implementation(libs.okhttp)

    // Kotlin serialization
    implementation(libs.kotlinxSerializationJson)
    implementation(libs.retrofitGsonConverter)
    implementation(libs.gson)

    //Consturcts hiltviewmodels()
    implementation(libs.hiltNavigationCompose)

    //coil
    implementation(libs.coilCompose)
    implementation(libs.coilGif) // GIF desteği için

    //firebase messaging
    implementation(libs.firebaseMessaging)
    implementation(libs.accompanistPager)
    implementation(libs.accompanistPagerIndicators)
    implementation(libs.pagingCommonAndroid)


}