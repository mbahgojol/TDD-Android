package com.blank.chapter10.data.model

data class ResponseLogin(
	val data: Data? = null,
	val success: Boolean? = null
)

data class Data(
	val id: String? = null,
	val email: String? = null,
	val username: String? = null,
	val token: String? = null
)

