plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinxSerialization)
}

android {
    namespace = "com.jg.tmbdapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.jg.tmbdapp"
        minSdk = 29
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
            buildConfigField( "String", "token",
                "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMjA4MGVhN2VlODk0NmM5MGUyOGIyOTBkNzlkM2ZiZiIsIm5iZiI6MTcyNTkwOTE2MC4yNzc1NTgsInN1YiI6IjYwZjA4ODMyNzQ2NDU3MDA0NjVmN2FiZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.8x9Tp32413IXu-NsH1bca62oFliZloE1odc03uh1q4g\"")
        }
        debug {
            isDebuggable = true
            buildConfigField( "String", "token",
                "\"eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMjA4MGVhN2VlODk0NmM5MGUyOGIyOTBkNzlkM2ZiZiIsIm5iZiI6MTcyNTkwOTE2MC4yNzc1NTgsInN1YiI6IjYwZjA4ODMyNzQ2NDU3MDA0NjVmN2FiZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.8x9Tp32413IXu-NsH1bca62oFliZloE1odc03uh1q4g\"")
            buildConfigField( "String", "base_img",
                "\"https://image.tmdb.org/t/p/w500\"")

        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
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
    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)
    implementation(libs.ktor.client.cio)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.client)
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.coil.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}