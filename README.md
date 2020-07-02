# JetHub

<img src="https://github.com/TakuSemba/JetHub/blob/master/art/logo.png">


<img src="https://github.com/TakuSemba/JetHub/blob/master/art/screen.gif" align="right" width="30%">

## What's JetHub? :rocket:

JetHub is a sample app using Github API and Jetpack components (LiveData, ViewModel, Room, Navigation etc...). 


The purpose of this app is to try new Android technologies and learn how it works and how it can be used in an app.


## Used Libraries
 - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)  (Observable data)
 - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) (Store and manage UI-related data)
 - [Kotlin Coroutine](https://github.com/Kotlin/kotlinx.coroutines) (Light-weight threads)
 - [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) (Fragment transitions)
 - [Dagger2](https://github.com/google/dagger) (Dependency Injection)
 - [Room](https://developer.android.com/topic/libraries/architecture/room) (Abstraction layer over SQLite)
 - [Retrofit](https://github.com/square/retrofit) (HTTP client)
 - [Ktx](https://developer.android.com/kotlin/ktx) (Kotlin extension)
 - [Mockk](https://github.com/mockk/mockk) (Unit testing)


</br>

<img src="https://github.com/TakuSemba/JetHub/blob/master/art/architecture.png" align="left" width="40%">


## Architecture

This app uses MVVM architecture. There is also a Repository layer, which is for interacting with API calls or DB transactions.

This is a single-activity application.

```kt
//--- Activity / Fragments ---//

viewModel.data.observe(this) { data ->
    // do fun things
}) 

//--- ViewModel ---//

val data: LiveData<Data>
repository.getData() // get data from API and/or DB

//--- Repository ---//

api.getData() // get data from API
db.getData() // get data from DB

```


## Threading

This app uses Kotlin coroutine to archive asynchronize programing. The layers below ViewModel are all suspend functions.

The basics of Kotlin coroutine can be learned from this [doc](https://github.com/Kotlin/kotlinx.coroutines/tree/master/docs).

## Github API Token

This project is based on GitHub API. You can set your own token in local.properties and app will use the token everytime app requests network calls to Github API. 

If you do not have a GitHub token, that's fine. You can use this app without token, but the number of API call is very limited by Github. You can see the details from this [link](https://developer.github.com/v3/#rate-limiting).

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
