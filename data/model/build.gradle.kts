import dependencies.Dep

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlinx-serialization")
}

android {
    compileSdkVersion(29)

    defaultConfig {
        minSdkVersion(25)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    
    implementation(Dep.Kotlin.stdlib)
    implementation(Dep.Kotlin.serializationRuntime)
    implementation(Dep.Kotlin.Coroutines.coroutines)
    implementation(Dep.Kotlin.Coroutines.coroutinesAndroid)
    implementation(Dep.AndroidX.appcompat)
    implementation(Dep.AndroidX.coreKtx)

    testImplementation(Dep.Test.junit)
    androidTestImplementation(Dep.Test.extJunit)
    androidTestImplementation(Dep.Test.espressoCore)

}