plugins {
  id("com.android.application")
  id("kotlin-android")
  id("kotlin-kapt")
}

android {
  compileSdkVersion(Config.compileSdkVersion)
  defaultConfig {
    applicationId = Config.packageName
    minSdkVersion(Config.minSdkVersion)
    targetSdkVersion(Config.targetSdkVersion)
    versionCode = Config.versionCode
    versionName = Config.versionName
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = true
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }
  dataBinding {
    isEnabled = true
  }
}

dependencies {
  // kotlin
  implementation(Dep.kotlin)

  // androidx
  implementation(Dep.appCompat)
  implementation(Dep.material)
  implementation(Dep.ktx)
  implementation(Dep.constraintLayout)

  // navigation
  implementation(Dep.navFragment)
  implementation(Dep.navUi)
  implementation(Dep.navFragmentKtx)
  implementation(Dep.navUiKtx)

  // dependency injection
  implementation(Dep.dagger)
  kapt(Dep.daggerCompiler)
  implementation(Dep.daggerAndroid)
  implementation(Dep.daggerAndroidSupport)
  kapt(Dep.daggerAndroidProcessor)

  // network
  implementation(Dep.retrofit)
  implementation(Dep.retrofitConverter)
  implementation(Dep.retrofitAdapter)
  debugImplementation(Dep.loggingInterceptor)

  // date
  implementation(Dep.threetenabp)

  // test
  testImplementation(Dep.junit)
}