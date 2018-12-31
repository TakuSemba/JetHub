# JetHub

<img src="https://github.com/TakuSemba/JetHub/blob/master/art/logo.png">


<br/>

<img src="https://github.com/TakuSemba/JetHub/blob/master/art/screen.gif" align="right" width="30%">

## What's JetHub? 

JetHub is a GitHub client app that uses Jetpack components (LiveData, ViewModel, Room, Navigation etc...). 


The purpose of this app is to try new Android technologies and learn how it works and how it can be used in an app.


## Used Libraries
 
 - Navigation (Fragment transitions)
 - Dagger2 (Dependency Injection)
 - Kotlin Coroutine (light-weight threads)
 - Room (Abstraction layer over SQLite)
 - Retrofit (HTTP client)
 - Mockk (Unit testing)


</br>
</br>
</br>

<img src="https://github.com/TakuSemba/JetHub/blob/master/art/architecture.png" align="left" width="40%">


## Architecture

This app uses MVVM architecture with a Repository layer, which is for interacting with API calls or DB transactions.

Also, this is a single-activity application.

```kt
//--- Activity / Fragments ---//

viewModel.data.observe(this, Observe { data ->
    // do fun things
}) 

//--- ViewModel ---//

val data: LiveData<Data>
repository.getData() // get data from API and/or DB

//--- Repository ---//

api.getData() // get data from API
db.getData() // get data from DB

```


</br>
</br>
</br>


## Threading

This app uses Kotlin coroutine to archive asynchronize calls. The layer below the ViewModel are all suspend functions.

The basics of Kotlin coroutine can be learned from this doc.

## Github API Token

This project is based on GitHub API. You can set your own token in local.properties and app will use the toekn everytime app requests network calls to Github API. 

If you do not have a GitHub token, that's fine. You can use this app without any token, but the number of the API call is very limited by Github. You can see the detail from this link.

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
