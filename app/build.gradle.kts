plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
}

kapt {
    correctErrorTypes = true
    useBuildCache = true
}

android {
    compileSdk = Versions.Android.compileSdk

    defaultConfig {
        minSdk = Versions.Android.minSdk
        targetSdk = Versions.Android.targetSdk
        versionCode = Versions.App.versionCode
        versionName = Versions.App.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_ROOT", Config.API_ROOT)

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            isDebuggable = true
        }
        release {
            isMinifyEnabled = true
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

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.JetpackCompose.jetpackCompose
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}'"
        }
    }
}

dependencies {
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.AndroidX.appCompat)
    implementation(Dependencies.AndroidX.lifecycleRuntime)
    implementation(Dependencies.material)

    implementation(Dependencies.JetpackCompose.ui)
    implementation(Dependencies.JetpackCompose.toolingPreview)
    implementation(Dependencies.JetpackCompose.foundation)
    implementation(Dependencies.JetpackCompose.material)
    implementation(Dependencies.JetpackCompose.activity)
    implementation(Dependencies.JetpackCompose.appCompatTheme)
    implementation(Dependencies.JetpackCompose.ratingBar)
    implementation(Dependencies.JetpackCompose.paging)
    implementation(Dependencies.JetpackCompose.swipeRefresh)
    implementation(Dependencies.JetpackCompose.icons)
    implementation(Dependencies.JetpackCompose.navigation)
    implementation(Dependencies.JetpackCompose.hiltNavigation)
    debugImplementation(Dependencies.JetpackCompose.tooling)

    implementation(Dependencies.Hilt.android)
    "kapt"(Dependencies.Hilt.compiler)

    implementation(Dependencies.Kotlinx.serialization)
    implementation(Dependencies.Kotlinx.coroutinesCore)
    implementation(Dependencies.Kotlinx.coroutineAndroid)

    implementation(Dependencies.Retrofit.core)
    implementation(Dependencies.Retrofit.okHttpLogging)
    implementation(Dependencies.Retrofit.serialization)

    testImplementation(Dependencies.Test.junit)

    androidTestImplementation(Dependencies.Test.runner)
    androidTestImplementation(Dependencies.Test.rules)
    androidTestImplementation(Dependencies.Test.testExt)
    androidTestImplementation(Dependencies.Test.composeJunit)
    debugImplementation(Dependencies.Test.composeUiTestManifest)
}
