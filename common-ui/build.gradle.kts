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
        freeCompilerArgs += "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi"
        freeCompilerArgs += "-Xopt-in=androidx.compose.material.ExperimentalMaterialApi"
        freeCompilerArgs += "-Xopt-in=androidx.compose.foundation.ExperimentalFoundationApi"
        freeCompilerArgs += "-Xopt-in=com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi"
    }
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libraries.AndroidX.Compose.version
    }
    packagingOptions {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }
}

dependencies {

    implementation(Libraries.Kotlin.jdk)

    api(Libraries.AndroidX.Compose.compiler)
    api(Libraries.AndroidX.Compose.foundation)
    api(Libraries.AndroidX.Compose.layout)
    api(Libraries.AndroidX.Compose.material)
    api(Libraries.AndroidX.Compose.runtime)
    debugApi(Libraries.AndroidX.Compose.tooling)
    api(Libraries.AndroidX.Compose.ui)
    api(Libraries.AndroidX.Compose.uiUtil)
    api(Libraries.AndroidX.Compose.preview)
    api(Libraries.AndroidX.Compose.animations)
    api(Libraries.AndroidX.Compose.activity)

    api(Libraries.AndroidX.ktxCore)

    api(Libraries.material)
    api(Libraries.AndroidX.appCompat)

    api(Libraries.AndroidX.Hilt.navigationCompose)

    api(Libraries.AndroidX.Navigation.runTime)
    api(Libraries.AndroidX.Navigation.core)

    api(Libraries.Accompanist.navigationAnimation)
    api(Libraries.Accompanist.swipeRefresh)
    api(Libraries.Accompanist.permission)
    api(Libraries.AndroidX.Hilt.navigationCompose)

}