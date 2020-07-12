package com.blank.chapter10.utils.api

import com.google.gson.annotations.SerializedName

data class BaseErrorDataSourceApi(
    @SerializedName("success")
    val success: Boolean? = null,
    @SerializedName("errors")
    val errors: String? = null
)