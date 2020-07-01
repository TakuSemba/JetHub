package com.takusemba.jethub.api.response

import com.takusemba.jethub.model.License
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * License response data.
 */
@Serializable
class LicenseResponse : DataResponse<License>() {

  @SerialName("key")
  var key: String? = null

  @SerialName("name")
  var name: String? = null

  @SerialName("spdx_id")
  var spdxId: String? = null

  @SerialName("url")
  var url: String? = null

  @SerialName("node_id")
  var nodeId: String? = null

  override fun toModel() = License(
    key = key ?: "",
    name = name ?: "",
    spdxId = spdxId ?: ""
  )
}