package com.blank.teamb_ex

import com.google.gson.annotations.SerializedName

data class BodyRegister(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
