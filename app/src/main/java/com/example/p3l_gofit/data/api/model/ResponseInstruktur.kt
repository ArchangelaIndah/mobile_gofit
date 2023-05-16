package com.example.p3l_gofit.data.api.model

import com.google.gson.annotations.SerializedName

data class ResponseInstruktur(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("token_type")
	val tokenType: String? = null,

	@field:SerializedName("user")
	val user: UserInstruktur
)

data class UserInstruktur(

	@field:SerializedName("nama_instruktur")
	val namaInstruktur: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("tanggal_lahir")
	val tanggalLahir: String? = null,

	@field:SerializedName("no_telepon")
	val noTelepon: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
