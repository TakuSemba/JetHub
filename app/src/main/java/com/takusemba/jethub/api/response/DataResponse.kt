package com.takusemba.jethub.api.response

abstract class DataResponse<T: Any> {

  abstract fun toModel(): T
}