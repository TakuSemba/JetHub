object Dep {

  object Version {
    // library
    const val buildGradle = "3.2.0"
    const val kotlin = "1.3.11"
    const val coroutine = "1.1.0"
    const val androidX = "1.0.0"
    const val lifecycle = "2.0.0"
    const val constraintLayout = "2.0.0-alpha2"
    const val navigation = "1.0.0-alpha09"
    const val dagger = "2.16"
    const val retrofit = "2.3.0"

    // library (test)
    const val junit = "4.12"
  }

  // plugin
  val pluginBuildGradle = "com.android.tools.build:gradle:${Version.buildGradle}"
  val pluginkotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"

  // kotlin
  val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"

  // coroutine
  val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutine}"
  val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutine}"

  // androidx
  val appCompat = "androidx.appcompat:appcompat:${Version.androidX}"
  val material = "com.google.android.material:material:${Version.androidX}"
  val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
  val coreKtx = "androidx.core:core-ktx:${Version.androidX}"
  val fragmentKtx = "androidx.fragment:fragment-ktx:${Version.androidX}"
  val collectionKtx = "androidx.collection:collection-ktx:${Version.androidX}"

  // navigation
  val navFragment = "android.arch.navigation:navigation-fragment:${Version.navigation}"
  val navUi = "android.arch.navigation:navigation-ui:${Version.navigation}"
  val navCommonKtx = "android.arch.navigation:navigation-common-ktx:${Version.navigation}"
  val navRuntimeKtx = "android.arch.navigation:navigation-runtime-ktx:${Version.navigation}"
  val navFragmentKtx = "android.arch.navigation:navigation-fragment-ktx:${Version.navigation}"
  val navUiKtx = "android.arch.navigation:navigation-ui-ktx:${Version.navigation}"
  val navTestKtx = "android.arch.navigation:navigation-testing-ktx:1.0.0-alpha01"

  // android architecture component
  val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Version.lifecycle}"
  val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle}"
  val lifecycleReactivestreams = "androidx.lifecycle:lifecycle-reactivestreams:${Version.lifecycle}"
  val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Version.lifecycle}"
  val viewModel = "androidx.lifecycle:lifecycle-viewmodel:${Version.lifecycle}"
  val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"

  // dependency injection
  val dagger = "com.google.dagger:dagger:${Version.dagger}"
  val daggerCompiler = "com.google.dagger:dagger-compiler:${Version.dagger}"
  val daggerAndroid = "com.google.dagger:dagger-android:${Version.dagger}"
  val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Version.dagger}"
  val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Version.dagger}"

  // network
  val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
  val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
  val retrofitAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
  val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:3.11.0"

  // date
  val threetenabp = "com.jakewharton.threetenabp:threetenabp:1.1.1"

  // test
  val junit = "junit:junit:${Version.junit}"
}
