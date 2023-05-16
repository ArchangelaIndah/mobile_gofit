package com.example.p3l_gofit.data.api.model

import com.google.gson.annotations.SerializedName

data class ResponseJadwalHarianInstruktur(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String? = null
)

data class FBookingKelas(

	@field:SerializedName("nama_kelas")
	val namaKelas: String? = null,

	@field:SerializedName("harga")
	val harga: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class FInstruktur(

	@field:SerializedName("nama_instruktur")
	val namaInstruktur: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

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

data class FJadwalUmum(

	@field:SerializedName("nama_kelas")
	val namaKelas: String? = null,

	@field:SerializedName("tanggal_kelas")
	val tanggalKelas: String? = null,

	@field:SerializedName("f_instruktur")
	val fInstruktur: FInstruktur? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("id_booking_kelas")
	val idBookingKelas: Int? = null,

	@field:SerializedName("f_booking_kelas")
	val fBookingKelas: FBookingKelas? = null,

	@field:SerializedName("hari_kelas")
	val hariKelas: String? = null,

	@field:SerializedName("id_instruktur")
	val idInstruktur: String? = null,

	@field:SerializedName("sesi_kelas")
	val sesiKelas: String? = null
)

data class DataItem(

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("tanggal_jadwal_harian")
	val tanggalJadwalHarian: String? = null,

	@field:SerializedName("jam_kelas")
	val jamKelas: String? = null,

	@field:SerializedName("date_created")
	val dateCreated: String? = null,

	@field:SerializedName("f_jadwal_umum")
	val fJadwalUmum: FJadwalUmum? = null,

	@field:SerializedName("id_jadwal")
	val idJadwal: Int? = null,

	@field:SerializedName("f_instruktur")
	val fInstruktur: FInstruktur? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("id_instruktur")
	val idInstruktur: String? = null
)
