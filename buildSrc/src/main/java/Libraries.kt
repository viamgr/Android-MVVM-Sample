object Config {
    const val applicationId = "com.mvvmexample.nearbyplaces"
    const val versionCode = 3
    const val versionName = "0.3.0"
}

object BuildPlugins {


    object GradleClassPath {
        const val kotlinVersion = "1.6.0"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.4"
        const val hiltGradlePlugin =
            "com.google.dagger:hilt-android-gradle-plugin:${Libraries.DaggerHilt.version}"
        const val kotlinSerialization =
            "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"
    }


    object Apply {
        const val androidApplication = "com.android.application"
        const val androidLibrary = "com.android.library"
        const val javaLibrary = "java-library"
        const val kotlinAndroid = "kotlin-android"
        const val kotlinParcelize = "kotlin-parcelize"
        const val kotlinKapt = "kotlin-kapt"
        const val kotlin = "kotlin"
        const val daggerHiltPlugin = "dagger.hilt.android.plugin"
        const val kotlinxSerialization = "kotlinx-serialization"
    }
}

object AndroidSdk {
    const val min = 21
    const val compile = 31
    const val target = compile
    const val buildToolsVersion = "31.0.0"
}

object Libraries {
    const val material = "com.google.android.material:material:1.5.0-alpha03"

    object DaggerHilt {
        const val version = "2.40.5"
        const val core = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val testing = "com.google.dagger:hilt-android-testing:$version"
    }

    object AndroidX {
        const val appCompat = "androidx.appcompat:appcompat:1.3.1"
        const val ktxCore = "androidx.core:core-ktx:1.6.0"

        object Hilt {
            private const val version = "1.0.0"
            const val compiler = "androidx.hilt:hilt-compiler:$version"
            const val navigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0-beta01"
        }

        object Compose {
            const val version = "1.1.0-beta04"

            const val compiler = "androidx.compose.compiler:compiler:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val material = "androidx.compose.material:material:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val runtimeLivedata = "androidx.compose.runtime:runtime-livedata:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val uiUtil = "androidx.compose.ui:ui-util:$version"
            const val preview = "androidx.compose.ui:ui-tooling-preview:$version"
            const val animations = "androidx.compose.animation:animation:$version"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
            const val activity = "androidx.activity:activity-compose:1.4.0"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val materialIconsExtended =
                "androidx.compose.material:material-icons-extended:$version"
        }

        object Navigation {
            const val version = "2.4.0-beta02"
            const val core = "androidx.navigation:navigation-compose:$version"
            const val runTime = "androidx.navigation:navigation-runtime-ktx:$version"
        }

        object Room {
            private const val version = "2.4.2"
            const val compiler = "androidx.room:room-compiler:$version"
            const val core = "androidx.room:room-ktx:$version"
            const val roomCommon = "androidx.room:room-common:$version"
            const val paging = "androidx.room:room-paging:$version"
        }

        object Paging {
            const val version = "3.1.0"
            const val runtime = "androidx.paging:paging-runtime-ktx:$version"
            const val common = "androidx.paging:paging-common-ktx:$version"
            const val compose = "androidx.paging:paging-compose:1.0.0-alpha14"
        }

    }

    object Accompanist {
        const val version = "0.20.3"
        const val permission = "com.google.accompanist:accompanist-permissions:$version"
        const val navigationAnimation =
            "com.google.accompanist:accompanist-navigation-animation:$version"
        const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:$version"
    }

    object Kotlin {
        const val jdk =
            "org.jetbrains.kotlin:kotlin-stdlib:${BuildPlugins.GradleClassPath.kotlinVersion}"

        object Coroutine {
            private const val version = "1.5.2"
            const val android =
                "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val core =
                "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        }
    }

    object Network {

        object OkHttp {
            private const val version = "4.9.2"
            const val core = "com.squareup.okhttp3:okhttp:$version"
            const val logger = "com.squareup.okhttp3:logging-interceptor:$version"
        }

        object Retrofit {
            private const val version = "2.9.0"
            private const val retrofitSerializationConverterVersion = "0.8.0"
            const val core = "com.squareup.retrofit2:retrofit:$version"
            const val retrofitKotlinXSerializationConvertor =
                "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:$retrofitSerializationConverterVersion"
        }
    }

    object Serializable {
        private const val kotlinxSerializationVersion = "1.3.2"
        const val kotlinxSerialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion"
    }

    object Json {
        private const val jsonVersion = "2.8.9"
        const val json = "com.google.code.gson:gson:$jsonVersion"
    }

}

object Modules {
    const val app = ":app"
    const val commonUi = ":common-ui"
    const val data = ":data"
    const val dataModel = ":data-model"
    const val model = ":model"
    const val domain = ":domain"

    object Ui {
        const val nearme = ":ui:nearme"
    }
}

