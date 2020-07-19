package dependencies

object Dep {

    object AndroidX {
        val appcompat = "androidx.appcompat:appcompat:1.1.0"
        val coreKtx = "androidx.core:core-ktx:1.3.0"
        val livedataKtx =  "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
        val livedataCoreKtx = "androidx.lifecycle:lifecycle-livedata-core-ktx:2.2.0"
        val constraint = "androidx.constraintlayout:constraintlayout:1.1.3"

        object Navigation {
            val version = "2.2.0"
            val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
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
        val assistedInjectAnnotations = "com.squareup.inject:assisted-inject-annotations-dagger2:0.5.2"
        val assistedInjectProcessor = "com.squareup.inject:assisted-inject-processor-dagger2:0.5.2"
    }

    object Groupie {
        val version = "2.4.0-alpha1"
        val groupie = "com.xwray:groupie:$version"
        val databinding = "com.xwray:groupie-databinding:$version"
    }

    object Kotlin {
        val version = "1.3.72"
        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
    }

    object Timber {
        val version = "4.7.1"
        val timber = "com.jakewharton.timber:timber:$version"
    }
}