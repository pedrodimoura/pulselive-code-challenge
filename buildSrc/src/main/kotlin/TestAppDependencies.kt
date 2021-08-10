object TestAppDependencies {

    const val junit = "junit:junit:${Versions.junit}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
    const val hiltAndroidTest = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val espressoIntent = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
    const val espressoContrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.mockWebServer}"
    const val okHttpIdlingResource =
        "com.jakewharton.espresso:okhttp3-idling-resource:${Versions.okHttpIdlingResource}"
    const val mockkAndroid = "io.mockk:mockk-android:${Versions.mockk}"
    const val dexMaker = "com.linkedin.dexmaker:dexmaker:${Versions.dexMaker}"

    const val navigation = "androidx.navigation:navigation-testing:${Versions.navigation}"
    const val coreKtxTesting = "androidx.test:core-ktx:${Versions.coreKtxTest}"
}
