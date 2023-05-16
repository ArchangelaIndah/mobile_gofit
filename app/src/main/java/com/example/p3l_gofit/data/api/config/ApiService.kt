package com.example.p3l_gofit.data.api.config

import com.example.p3l_gofit.data.api.model.*
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("loginMember")
    suspend fun loginMember(
        @Field("id") id: String,
        @Field("password") password: String
    ): ResponseMember

    @FormUrlEncoded
    @POST("loginInstruktur")
    suspend fun loginInstruktur(
        @Field("nama_instruktur") email: String,
        @Field("password") password: String
    ): ResponseInstruktur

    @FormUrlEncoded
    @POST("loginPegawai")
    suspend fun loginPegawai(
        @Field("nama_pegawai") namaPegawai: String,
        @Field("password") password: String
    ): ResponsePegawai


    @GET("showjadwalharianbyinstruktur/{id}")
    suspend fun showJadwalHarianInstruktur(
        @Path("id") id: String
    ): ResponseJadwalHarianInstruktur

    @GET("showhistoriperizinanbyid/{id}")
    suspend fun showHistoriPerizinanById(
        @Path("id") id: String
    ): ResponseHistoryPerizinanById

    @FormUrlEncoded
    @POST("izinInstruktur")
    suspend fun izinInstruktur(
        @Field("id_jadwal_harian") idJadwalHarian: Int,
        @Field("id_instruktur") idInstruktur: String,
    ): ResponseInstruktur

}