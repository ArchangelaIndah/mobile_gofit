package com.example.p3l_gofit.data.di

import android.content.Context
import com.example.p3l_gofit.data.api.config.ApiConfig
import com.example.p3l_gofit.data.repo.UsersRepository

object Injection {
    fun provideRepository(context: Context): UsersRepository {
        val apiService = ApiConfig.getApiService()
        return UsersRepository.getInstance(apiService)
    }


}