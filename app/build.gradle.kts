import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

  // coroutine
  implementation(Dep.coroutineCore)
  implementation(Dep.coroutineAndroid)

  // androidx
  implementation(Dep.appCompat)
  implementation(Dep.material)
  implementation(Dep.constraintLayout)
  implementation(Dep.coreKtx)
  implementation(Dep.fragmentKtx)
  implementation(Dep.collectionKtx)

  // android architecture component
  implementation(Dep.lifecycleRuntime)
  implementation(Dep.lifecycleExtensions)
  implementation(Dep.lifecycleReactivestreams)
  kapt(Dep.lifecycleCompiler)
  implementation(Dep.viewModel)
  implementation(Dep.viewmodelKtx)

  // navigation
  implementation(Dep.navFragment)
  implementation(Dep.navUi)
  implementation(Dep.navCommonKtx)
  implementation(Dep.navRuntimeKtx)
  implementation(Dep.navFragmentKtx)
  implementation(Dep.navUiKtx)
  testImplementation(Dep.navTestKtx)

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