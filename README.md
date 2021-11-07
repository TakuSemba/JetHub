<p align="center">
<img src="https://github.com/TakuSemba/JetHub/blob/master/screenshots/banner.png" width=250>
</p>

<H3 align="center">
Android Sample App using Github API and Jetpack Component.</br>
</H3>

<br/>

## What's JetHub? :rocket:

<img src="https://github.com/TakuSemba/JetHub/blob/master/screenshots/screen.gif" align="right" width="280">

JetHub is a sample app using Github API and Jetpack components.

The purpose of this project is to try new Android technologies and learn how it works in an app.

## Used Libraries
 - [Navigation](https://developer.android.com/topic/libraries/architecture/navigation) (Fragment transitions)
 - [View Binding](https://developer.android.com/topic/libraries/view-binding) (Bind views)
 - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) (Store and manage UI-related data)
 - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)  (Observable data)
 - [Jetpack Compose](https://developer.android.com/jetpack/compose) (Declarative UI)
 - [Kotlin Coroutine](https://github.com/Kotlin/kotlinx.coroutines) (Light-weight threads)
 - [Dagger2](https://github.com/google/dagger) (Dependency Injection)
 - [Hilt](https://dagger.dev/hilt/) (Dependency Injection for Android)
 - [Room](https://developer.android.com/topic/libraries/architecture/room) (Abstraction layer over SQLite)
 - [Retrofit](https://github.com/square/retrofit) (HTTP client)
 - [Mockk](https://github.com/mockk/mockk) (Unit testing)
 
<br/>
<br/>
<br/>
<br/>

<img src="https://github.com/TakuSemba/JetHub/blob/master/screenshots/modules.png" align="left" width="400">

## Multi Module / Dynamic Feature Module


This project consists of multiple modules and some of them are provided as Dynamic Feature Module.

**Feature Module**

`:feed`, `:search`, `:pin` are feature modules. These are included in base.apk and does not require user to download one later.

**Dynamic Feature Module**

`:repo`, `:developer` are [dynamic feature modules](https://developer.android.com/guide/app-bundle/dynamic-delivery). These are not included in base.apk and require user to download one on demand.

<br/>

## Architecture

<img src="https://github.com/TakuSemba/JetHub/blob/master/screenshots/architecture.png" align="right" width="360">

This app is a single-activity application and uses MVVM architecture and follows the guildline shown [here](https://developer.android.com/jetpack/docs/guide).

```kt
//--- Activity / Fragments / Compose ---//

lifecycleScope.launch {
  viewModel.uiState
    .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
    .collect { data -> /* do something */ }
}

val uiState by repoViewModel.uiState.collectAsState()

//--- ViewModel ---//

val uiState: StateFlow<UiState>
repository.getData() // get data from API and/or DB

//--- Repository ---//

api.getData() // get data from API
db.getData() // get data from DB
```

<br/>
<br/>

## :construction: Jetpack Compose (Under Development) :construction:

[Jetpack Compose](https://developer.android.com/jetpack/compose) is used for the screens of `:repo`, `:developer` modules. Therefore, To build this project, you will need at lease 2020.3.1 version of Android Studio.
I'm currently migrating to Jetpack Compose and the design is in progress for now.

## Dark Theme

Dark Theme is supported. You can toggle theme by tapping the theme icon on the top right corner. The selected theme would be retained in application scope for the sake of demo application.

## Github API Token

This project is based on GitHub API. You can set your own token in local.properties and JetHub will use the token for every single request to Github.
In order to generate a token, you can do so from [your settings page](https://github.com/settings/tokens).

This app works without a token, but the number of API call is very limited by Github. You can see the details from this [link](https://developer.github.com/v3/#rate-limiting).

```local.properties
// local.properties
api_token=xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
```

## Author

* **Taku Semba**
    * **Github** - (https://github.com/takusemba)
    * **Twitter** - (https://twitter.com/takusemba)
    * **Facebook** - (https://www.facebook.com/takusemba)

## Licence
```
Copyright 2017 Taku Semba.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
