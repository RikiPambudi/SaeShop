plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.saeshop"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.saeshop"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file("keystore.jks")       // Path ke keystore
            storePassword = "SaeShop"              // Kata sandi keystore
            keyAlias = "keystore"                   // Alias kunci
            keyPassword = "SaeShop"                // Kata sandi kunci
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false               // Optimasi APK
            isShrinkResources = false             // Jangan kompresi resource
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), // File proguard default
                "proguard-rules.pro"                               // File proguard custom
            )
            signingConfig = signingConfigs.getByName("release") // Menetapkan signingConfig
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation("com.google.android.material:material:1.11.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
