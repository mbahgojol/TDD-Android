package com.blank.chapter10.data.model

data class ResponseRegister(
    val data: DataRegister? = null,
    val success: Boolean? = null
)

data class DataRegister(
    val id: String? = null,
    val email: String? = null,
    val username: String? = null
)

