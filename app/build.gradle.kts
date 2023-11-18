plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("com.google.devtools.ksp")
    id ("kotlin-kapt")
}

android {
    namespace = "dev.soul.effectivemobtest"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.soul.effectivemobtest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    viewBinding {
        this.enable = true
    }
    dataBinding {
        this.enable = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //module implementation
    implementation(project(":data"))
    implementation(project(":domain"))

//viewmodel livedata
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    //navigation component
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")

//    // Dagger Android
//    api("com.google.dagger:dagger-android:2.43.2")
//    api("com.google.dagger:dagger-android-support:2.43.2")
//
//    kapt("com.google.dagger:dagger-android-processor:2.43.2")
//    kapt("com.google.dagger:dagger-compiler:2.43.2")
//
//    implementation("com.google.dagger:dagger:2.43.2")
//    implementation("com.google.dagger:dagger-android-support:2.28")
//    implementation("com.google.dagger:dagger-android:2.35.1")
    //hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")
    implementation("androidx.hilt:hilt-navigation-fragment:1.1.0")

    //glide
    implementation("com.github.bumptech.glide:glide:4.13.2")
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.2")

    // Chuck
    debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")
    releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")

    // dots
    implementation("com.tbuonomo:dotsindicator:5.0")

    //flex box
    implementation("com.google.android.flexbox:flexbox:3.0.0")

    //masked edittext
    implementation("com.github.santalu:mask-edittext:1.0.2")

    //expension
    implementation("com.github.florent37:expansionpanel:1.2.4")

    implementation("io.coil-kt:coil:2.4.0")

}