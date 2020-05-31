object Dep {

  object Version {
    // library
    const val buildGradle = "4.0.0"
    const val kotlin = "1.3.50"
    const val coroutine = "1.3.1"
    const val androidX = "1.0.0"
    const val lifecycle = "2.1.0"
    const val constraintLayout = "2.0.0-beta6"
    const val navigation = "2.1.0"
    const val room = "2.1.0"
    const val dagger = "2.24"
    const val retrofit = "2.6.1"
    const val okhttp = "4.2.0"
    const val groupie = "2.5.1"

    // library (test)
    const val junit = "4.12"
  }

  // plugin
  val pluginBuildGradle = "com.android.tools.build:gradle:${Version.buildGradle}"
  val pluginKotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
  val pluginSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navigation}"

  // kotlin
  val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Version.kotlin}"

  // coroutine
  val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutine}"
  val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutine}"
  val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutine}"

  // androidx
  val appCompat = "androidx.appcompat:appcompat:${Version.androidX}"
  val material = "com.google.android.material:material:${Version.androidX}"
  val constraintLayout = "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}"
  val activityKtx = "androidx.activity:activity-ktx:${Version.androidX}"
  val fragmentKtx = "androidx.fragment:fragment-ktx:${Version.androidX}"

  // navigation
  val navCommonKtx = "androidx.navigation:navigation-common-ktx:${Version.navigation}"
  val navRuntimeKtx = "androidx.navigation:navigation-runtime-ktx:${Version.navigation}"
  val navFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Version.navigation}"
  val navUiKtx = "androidx.navigation:navigation-ui-ktx:${Version.navigation}"

  // lifecycle
  val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Version.lifecycle}"
  val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycle}"
  val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Version.lifecycle}"
  val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
  val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifecycle}"

  // dependency injection
  val dagger = "com.google.dagger:dagger:${Version.dagger}"
  val daggerCompiler = "com.google.dagger:dagger-compiler:${Version.dagger}"
  val daggerAndroid = "com.google.dagger:dagger-android:${Version.dagger}"
  val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Version.dagger}"
  val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Version.dagger}"

  // network
  val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
  val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"
  val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
  val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Version.okhttp}"

  // storage
  val roomRuntime = "androidx.room:room-runtime:${Version.room}"
  val roomCompiler = "androidx.room:room-compiler:${Version.room}"
  val roomKtx = "androidx.room:room-ktx:${Version.room}"

  // recycler view
  val groupie = "com.xwray:groupie:${Version.groupie}"
  val groupieDatabinding = "com.xwray:groupie-databinding:${Version.groupie}"

  // date
  val threetenabp = "com.jakewharton.threetenabp:threetenabp:1.1.1"

  // ui
  val circleImage = "de.hdodenhof:circleimageview:2.2.0"

  // image
  val picasso = "com.squareup.picasso:picasso:2.71828"

  // test
  val junit = "junit:junit:${Version.junit}"
  val mockk = "io.mockk:mockk:1.9.3"
  val truth = "com.google.truth:truth:1.0"
  val testCore = "android.arch.core:core-testing:1.1.0"
  val orgThreetenbp = "org.threeten:threetenbp:1.3.3"
}
