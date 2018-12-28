package com.takusemba.jethub.api.response

import com.google.gson.annotations.SerializedName
import com.takusemba.jethub.model.License

class LicenseResponse : DataResponse<License>() {

  @SerializedName("key")
  var key: String? = null

  @SerializedName("name")
  var name: String? = null

  @SerializedName("spdx_id")
  var spdxId: String? = null

  @SerializedName("url")
  var url: String? = null

  @SerializedName("node_id")
  var nodeId: String? = null

  override fun toModel() = License(
    key = key ?: "",
    name = name ?: "",
    spdxId = spdxId ?: ""
  )
}