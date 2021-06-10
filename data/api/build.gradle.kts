import dependencies.Dep
import java.util.Properties

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
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

        /**
         * TODO
         */
        val properties = Properties()
        properties.load(project.rootProject.file("local.properties").inputStream())
        val API_KEY = properties.getProperty("api_key")
        buildConfigField("String", "API_KEY", "\"${API_KEY}\"")
    }

    buildTypes {
        getByName("release") {
            minifyEnabled(false)
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

}

dependencies {
    implementation(project(":corecomponent:core"))
    implementation(project(":data:model"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(Dep.Kotlin.stdlib)
    implementation(Dep.Kotlin.serializationRuntime)
    implementation(Dep.Ktor.clientAndroid)
    implementation(Dep.Ktor.clientOkhttp)
    implementation(Dep.Ktor.clientSerializationJvm)
    implementation(Dep.AndroidX.appcompat)
    implementation(Dep.AndroidX.coreKtx)
    implementation(Dep.Kotlin.Coroutines.coroutines)
    implementation(Dep.Kotlin.Coroutines.coroutinesAndroid)

    implementation(Dep.Dagger.core)
    implementation(Dep.Dagger.androidSupport)
    implementation(Dep.Dagger.android)
    kapt(Dep.Dagger.compiler)
    kapt(Dep.Dagger.androidProcessor)
    compileOnly(Dep.Dagger.assistedInjectAnnotations)
    kapt(Dep.Dagger.assistedInjectProcessor)

    implementation(Dep.Timber.timber)

    testImplementation(Dep.Test.junit)
    androidTestImplementation(Dep.Test.extJunit)
    androidTestImplementation(Dep.Test.espressoCore)

}