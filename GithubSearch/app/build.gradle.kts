plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("de.mannodermaus.android-junit5")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.searchdemo"
        minSdk = 23
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    dataBinding {
        isEnabled = true
    }

    lint {
        disable.addAll(listOf("NotifyDataSetChanged", "CheckResult"))
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.6.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0-rc01")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.0-rc01")

    //region UI
    implementation("com.airbnb.android:lottie:5.1.1")
    implementation("com.google.android.material:material:1.6.0")
    implementation("com.afollestad.material-dialogs:core:3.3.0")
    //endregion

    //region Network
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //endregion

    //region Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")
    //endregion

    //region Utils
    implementation("com.jakewharton.threetenabp:threetenabp:1.4.0")
    implementation("io.insert-koin:koin-android:3.2.0")
    implementation("io.insert-koin:koin-android-compat:3.2.0")
    implementation("io.insert-koin:koin-androidx-workmanager:3.2.0")
    implementation("io.insert-koin:koin-androidx-navigation:3.2.0")
    //endregion

    //region Test
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")      // (Required) Writing and executing Unit Tests on the JUnit Platform
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")  // (Optional) If you need "Parameterized Tests"
    testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.8.2")      // (Optional) If you also have JUnit 4-based tests
    testImplementation("com.google.truth:truth:1.1.3")
    //endregion
}