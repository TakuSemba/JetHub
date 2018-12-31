package com.takusemba.jethub.api.response

import com.google.gson.annotations.SerializedName

/**
 * Response data with [items] in it.
 */
class ListResponse<T : Any> {

  @SerializedName("total_count")
  var totalCount: Int? = null

  @SerializedName("incomplete_results")
  var incompleteResults: Boolean? = null

  @SerializedName("items")
  var items: List<T>? = null
}