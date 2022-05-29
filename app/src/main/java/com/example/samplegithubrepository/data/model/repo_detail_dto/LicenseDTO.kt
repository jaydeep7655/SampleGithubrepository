package com.example.samplegithubrepository.data.model.repo_detail_dto


import com.google.gson.annotations.SerializedName

data class LicenseDTO(
    @SerializedName("key")
    val key: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("node_id")
    val nodeId: String?,
    @SerializedName("spdx_id")
    val spdxId: String?,
    @SerializedName("url")
    val url: Any
)