plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.rururi.easyprompt"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.rururi.easyprompt"
        minSdk = 24
        targetSdk = 35
        versionCode = 4
        versionName = "1.0.0"

        testInstrumentationRunner = "com.rururi.easyprompt.HiltTestRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file("keystore/release.keystore.jks")
            storePassword = "2412ababyass"
            keyAlias = "key0"
            keyPassword = "2412ababyass"
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
        languageVersion = "2.0"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    //Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
    //Hiltテスト用
    androidTestImplementation(libs.hilt.testing)
    kaptAndroidTest(libs.hilt.compiler)


    //カラーピッカー
    implementation(libs.compose.colorpicker)
    //ナビゲーション
    implementation(libs.androidx.navigation.compose)            //navigation
    androidTestImplementation(libs.androidx.navigation.testing)   //navigationTest
    //M3関係
    implementation(libs.androidx.material.icons.extended)   //アイコンを使用する場合
    debugImplementation(libs.ui.tooling)    //プレビュー用

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    //デフォルト
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}