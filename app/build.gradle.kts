
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.jyldyzferr.composemoviesapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.jyldyzferr.composemoviesapp"
        minSdk = 28
        targetSdk = 33
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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}

dependencies {
    implementation("com.google.accompanist:accompanist-pager-indicators:0.30.1")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.0-rc01")

    implementation("androidx.hilt:hilt-navigation-compose:1.0.0-alpha02")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("com.google.firebase:firebase-annotations:16.2.0")
    implementation("com.android.car.ui:car-ui-lib:2.5.1")

    //Room
    val room_version = ("2.5.2")
    kapt("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-ktx:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    implementation("androidx.room:room-runtime:$room_version")

   // ksp("androidx.room:room-compiler:$room_version")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")

    //Coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    //livedata
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // Accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
    implementation("com.google.accompanist:accompanist-navigation-animation:0.31.2-alpha")


    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Icons
    implementation("androidx.compose.material:material-icons-extended:1.4.3")

    implementation("androidx.navigation:navigation-compose:2.4.0-alpha06")
    implementation("androidx.compose.material:material:1.4.2")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    // implementation("androidx.wear.compose:compose-material:1.2.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}