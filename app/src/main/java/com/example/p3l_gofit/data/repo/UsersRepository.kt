package com.example.p3l_gofit.data.repo

import com.example.p3l_gofit.data.api.config.ApiService
import com.example.p3l_gofit.data.api.model.ResponseMember

class UsersRepository private constructor(
    private val userApi: ApiService
) {

    suspend fun loginMember(id:String, password: String) : ResponseMember {
       return userApi.loginMember(id,password)
    }

    suspend fun loginInstruktur(
        email: String,
        password: String
    ) = userApi.loginInstruktur(email, password)

    suspend fun loginPegawai(
        namaPegawai: String,
        password: String
    ) = userApi.loginPegawai(namaPegawai, password)

    suspend fun getJadwalHarianInstruktur(
        id: String
    ) = userApi.showJadwalHarianInstruktur(id)

    suspend fun getHistoriPerizinanById(
        id: String
    ) = userApi.showHistoriPerizinanById(id)

    suspend fun izinInstruktur(
        idJadwalHarian: Int,
        idInstruktur: String,
    ) = userApi.izinInstruktur(idJadwalHarian, idInstruktur)

    companion object {
        @Volatile
        private var instance: UsersRepository? = null
        fun getInstance(
            apiService: ApiService,
        ): UsersRepository =
            instance ?: synchronized(this) {
                instance ?: UsersRepository(apiService)
            }.also { instance = it }
    }
}