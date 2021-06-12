package dependencies

object Dep {

    object GradlePlugin {
        val android = "com.android.tools.build:gradle:4.1.3"
        val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
        val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Kotlin.version}"
        val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${AndroidX.Navigation.version}"
    }

    object AndroidX {
        val appcompat = "androidx.appcompat:appcompat:1.1.0"
        val coreKtx = "androidx.core:core-ktx:1.3.0"
        val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:2.2.0"
        val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
        val livedataCoreKtx = "androidx.lifecycle:lifecycle-livedata-core-ktx:2.2.0"
        val constraint = "androidx.constraintlayout:constraintlayout:1.1.3"

        object Navigation {
            val version = "2.2.0"
            val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
        }

        object Room {
            val version = "2.2.5"
            val roomKtx = "androidx.room:room-ktx:$version"
            val roomRuntime = "androidx.room:room-runtime:$version"
            val roomCompiler = "androidx.room:room-compiler:$version"
        }
    }

    object Coil {
        val version = "0.8.0"
        val coil = "io.coil-kt:coil:$version"
    }

    object Dagger {
        val version = "2.25.4"
        val core = "com.google.dagger:dagger:$version"
        val compiler = "com.google.dagger:dagger-compiler:$version"
        val androidSupport = "com.google.dagger:dagger-android-support:$version"
        val android = "com.google.dagger:dagger-android:$version"
        val androidProcessor = "com.google.dagger:dagger-android-processor:$version"
        val assistedInjectAnnotations =
            "com.squareup.inject:assisted-inject-annotations-dagger2:0.5.2"
        val assistedInjectProcessor = "com.squareup.inject:assisted-inject-processor-dagger2:0.5.2"
    }

    object Groupie {
        val version = "2.8.1"
        val groupie = "com.xwray:groupie:$version"
        val groupieKotlinExtensions = "com.xwray:groupie-kotlin-android-extensions:$version"
        val databinding = "com.xwray:groupie-databinding:$version"
    }

    object Ktor {
        val version = "1.3.2"
        val clientOkhttp = "io.ktor:ktor-client-okhttp:$version"
        val clientAndroid = "io.ktor:ktor-client-android:$version"
        val clientSerializationJvm = "io.ktor:ktor-client-serialization-jvm:$version"
    }

    object Kotlin {
        val version = "1.3.72"
        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        val serializationRuntime = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0"

        object Coroutines {
            val version = "1.3.4"
            val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        }
    }

    object Stetho {
        val stetho = "com.facebook.stetho:stetho:1.5.1"
        val stethoHttp = "com.facebook.stetho:stetho-okhttp3:1.5.1"
    }

    object Timber {
        val version = "4.7.1"
        val timber = "com.jakewharton.timber:timber:$version"
    }

    object Test {
        val junit = "junit:junit:4.12"
        val extJunit = "androidx.test.ext:junit:1.1.1"
        val espressoCore = "androidx.test.espresso:espresso-core:3.2.0"

        object Mockk {
            val version = "1.11.0"
            val mockk = "io.mockk:mockk:$version"
        }

        object Coroutines {
            val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Kotlin.Coroutines.version}"
        }

    }
}