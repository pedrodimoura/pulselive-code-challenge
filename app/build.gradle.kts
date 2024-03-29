plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

repositories {
    google()
    mavenCentral()
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        applicationId("com.github.pedrodimoura.pulselivecodechallenge")
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner("com.github.pedrodimoura.pulselivecodechallenge.common.HiltAndroidRunner")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "BASE_URL", "\"http://dynamic.pulselive.com/test/native/\"")
        }
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            buildConfigField("String", "BASE_URL", "\"http://dynamic.pulselive.com/test/native/\"")
        }
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        viewBinding = true
    }

    sourceSets {
        getByName("androidTest").assets.srcDirs("src/debug/assets")
    }

    packagingOptions {
        exclude("META-INF/metadata.jvm.kotlin_module")
        exclude("META-INF/gradle/incremental.annotation.processors")
        exclude("META-INF/metadata.kotlin_module")
    }
}

dependencies {
    implementation(AppDependencies.kotlinStdLib)
    implementation(AppDependencies.coreKtx)
    implementation(AppDependencies.appCompat)
    implementation(AppDependencies.material)
    implementation(AppDependencies.constraintLayout)
    implementation(AppDependencies.fragmentKtx)
    implementation(AppDependencies.activityKtx)
    implementation(AppDependencies.hilt)
    kapt(AppDependencies.hiltCompiler)
    kapt(AppDependencies.hiltAndroidXCompiler)
    implementation(AppDependencies.retrofit)
    implementation(AppDependencies.httpLoggingInterceptor)
    implementation(AppDependencies.gson)
    implementation(AppDependencies.coroutines)
    implementation(AppDependencies.viewModel)
    implementation(AppDependencies.liveData)
    implementation(AppDependencies.gsonConverter)
    implementation(AppDependencies.navigationFragmentKtx)
    implementation(AppDependencies.navigationUIKtx)

    testImplementation(TestAppDependencies.junit)
    testImplementation(TestAppDependencies.mockk)
    testImplementation(TestAppDependencies.coroutines)
    testImplementation(TestAppDependencies.coreTesting)
    kaptTest(TestAppDependencies.hiltCompiler)
    androidTestImplementation(TestAppDependencies.junitExt)
    androidTestImplementation(TestAppDependencies.espresso)

    androidTestImplementation(TestAppDependencies.hiltAndroidTest)
    kaptAndroidTest(TestAppDependencies.hiltCompiler)
    androidTestImplementation(TestAppDependencies.espressoIntent)
    androidTestImplementation(TestAppDependencies.espressoContrib)
    androidTestImplementation(TestAppDependencies.mockWebServer)
    androidTestImplementation(TestAppDependencies.okHttpIdlingResource)
    androidTestImplementation(TestAppDependencies.mockkAndroid)
    androidTestImplementation(TestAppDependencies.dexMaker)
    androidTestImplementation(TestAppDependencies.navigation)
    androidTestImplementation(TestAppDependencies.coreKtxTesting)
}
