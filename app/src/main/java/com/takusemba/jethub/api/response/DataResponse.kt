package com.takusemba.jethub.api.response

abstract class DataResponse<T> {

  abstract fun toModel(): T
}