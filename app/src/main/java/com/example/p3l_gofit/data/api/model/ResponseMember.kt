package com.example.p3l_gofit.data.api.model

import com.google.gson.annotations.SerializedName

data class ResponseMember(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("user")
	val user: UserMember,

	@field:SerializedName("token")
	val token: String? = null
)

data class UserMember(

	@field:SerializedName("deposit_uang")
	val depositUang: Int? = null,

	@field:SerializedName("nama")
	val nama: String,

	@field:SerializedName("deposit_kelas")
	val depositKelas: Int? = null,

	@field:SerializedName("masa_berlaku")
	val masaBerlaku: String? = null,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("tanggal_lahir")
	val tanggalLahir: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("no_telepon")
	val noTelepon: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("status")
	val status: Int? = null
)
