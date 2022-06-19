plugins {
    id(BuildPlugins.Apply.androidLibrary)
    id(BuildPlugins.Apply.kotlinAndroid)
    id(BuildPlugins.Apply.kotlinKapt)
    id(BuildPlugins.Apply.kotlinParcelize)
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
    }
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

dependencies {
    implementation(Libraries.Serializable.kotlinxSerialization)

    api(Libraries.AndroidX.Room.roomCommon)
    api(Libraries.AndroidX.Room.core)
    kapt(Libraries.AndroidX.Room.compiler)
}