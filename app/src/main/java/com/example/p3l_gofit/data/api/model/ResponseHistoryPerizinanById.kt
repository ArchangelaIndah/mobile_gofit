package com.example.p3l_gofit.data.api.model

import com.google.gson.annotations.SerializedName

data class ResponseHistoryPerizinanById(

	@field:SerializedName("data")
	val data: List<DataItem2>,

	@field:SerializedName("message")
	val message: String
)

data class FBookingKelas2(

	@field:SerializedName("nama_kelas")
	val namaKelas: String,

	@field:SerializedName("harga")
	val harga: String,

	@field:SerializedName("id")
	val id: Int
)

data class DataItem2(

	@field:SerializedName("id_jadwal_harian")
	val idJadwalHarian: Int,

	@field:SerializedName("tanggal_izin")
	val tanggalIzin: String,

	@field:SerializedName("keterangan")
	val keterangan: String? = null,

	@field:SerializedName("id_instruktur_pengganti")
	val idInstrukturPengganti: String? = null,

	@field:SerializedName("f_instruktur_pengganti")
	val FInstrukturPengganti: FInstruktur? = null,

	@field:SerializedName("f_instruktur")
	val FInstruktur: FInstruktur,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("f_jadwal_harian")
	val fJadwalHarian: FJadwalHarian,

	@field:SerializedName("id_instruktur")
	val idInstruktur: String,

	@field:SerializedName("status")
	val status: Int
)

data class FJadwalHarian(

	@field:SerializedName("keterangan")
	val keterangan: String,

	@field:SerializedName("tanggal_jadwal_harian")
	val tanggalJadwalHarian: String,

	@field:SerializedName("jam_kelas")
	val jamKelas: String,

	@field:SerializedName("date_created")
	val dateCreated: String,

	@field:SerializedName("f_jadwal_umum")
	val FJadwalUmum: FJadwalUmum,

	@field:SerializedName("id_jadwal")
	val idJadwal: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("id_instruktur")
	val idInstruktur: String
)



