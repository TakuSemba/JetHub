package com.takusemba.jethub.extension

import com.takusemba.jethub.model.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

//fun <T> CoroutineScope.launchCatching(
//  block: suspend CoroutineScope.() -> T
//): Result<T> {
//  launch {
//    try {
//      val result = block()
//      return Result.Success(result)
//    } catch (e: Exception) {
//      Result.Error(e)
//    }
//  }
//}
