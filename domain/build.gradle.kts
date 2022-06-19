plugins {
    id(BuildPlugins.Apply.androidLibrary)
    id(BuildPlugins.Apply.kotlinAndroid)
    id(BuildPlugins.Apply.kotlinKapt)
}

android {
    compileSdk = AndroidSdk.compile
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

dependencies {
    api(project(Modules.model))
    implementation(Libraries.DaggerHilt.core)
    kapt(Libraries.DaggerHilt.compiler)

    api(Libraries.Kotlin.Coroutine.core)
    api(Libraries.Kotlin.Coroutine.android)

    api(Libraries.AndroidX.Paging.runtime)
}