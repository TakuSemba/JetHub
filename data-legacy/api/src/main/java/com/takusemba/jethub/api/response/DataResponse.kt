package com.takusemba.jethub.api.response

/**
 * Response data from API calls.
 */
abstract class DataResponse<T : Any> {

  /**
   * Response data can be converted into [T] via this method.
   */
  abstract fun toModel(): T
}