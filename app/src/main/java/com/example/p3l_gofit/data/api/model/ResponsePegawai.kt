package com.example.p3l_gofit.data.api.model

import com.google.gson.annotations.SerializedName

data class ResponsePegawai(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("token_type")
	val tokenType: String? = null,

	@field:SerializedName("user")
	val user: User
)

data class User(

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("nama_pegawai")
	val namaPegawai: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("tanggal_lahir")
	val tanggalLahir: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("no_telepon")
	val noTelepon: String? = null
)
