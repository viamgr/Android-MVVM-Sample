plugins {
    id(BuildPlugins.Apply.androidLibrary)
    id(BuildPlugins.Apply.kotlinAndroid)
    id(BuildPlugins.Apply.kotlinKapt)
    id(BuildPlugins.Apply.daggerHiltPlugin)
    id(BuildPlugins.Apply.kotlinxSerialization)
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
        freeCompilerArgs += "-Xopt-in=org.jetbrains.kotlinx.serialization.ExperimentalSerializationApi"
        freeCompilerArgs += "-Xopt-in=androidx.paging.ExperimentalPagingApi"
    }
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

dependencies {
    implementation(project(Modules.domain))
    implementation(project(Modules.dataModel))

    implementation(Libraries.DaggerHilt.core)
    kapt(Libraries.DaggerHilt.compiler)
    implementation(project(Modules.dataModel))
    implementation(Libraries.AndroidX.ktxCore)

    implementation(Libraries.Network.OkHttp.core)
    implementation(Libraries.Network.OkHttp.logger)

    api(Libraries.Network.Retrofit.core)
    implementation(Libraries.Network.OkHttp.core)
    implementation(Libraries.Network.OkHttp.logger)
    implementation(Libraries.Network.Retrofit.retrofitKotlinXSerializationConvertor)

    implementation(Libraries.Serializable.kotlinxSerialization)
    implementation(Libraries.Json.json)

    kapt(Libraries.AndroidX.Room.compiler)
    implementation(Libraries.AndroidX.Room.paging)


    implementation(Libraries.AndroidX.Paging.common)
    implementation(Libraries.AndroidX.Room.paging)
}