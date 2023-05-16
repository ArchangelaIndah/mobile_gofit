package com.example.p3l_gofit.ui.activity.home.homeInstruktur

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p3l_gofit.data.api.model.ResponseHistoryPerizinanById
import com.example.p3l_gofit.data.api.model.ResponseInstruktur
import com.example.p3l_gofit.data.api.model.ResponseJadwalHarianInstruktur
import com.example.p3l_gofit.data.repo.Result
import com.example.p3l_gofit.data.repo.UsersRepository
import kotlinx.coroutines.launch

class HomeInstrukturViewModel(private val repository: UsersRepository) : ViewModel() {

    private val _resultJadwalHarian = MutableLiveData<Result<ResponseJadwalHarianInstruktur>>()
    val resultJadwalHarian: LiveData<Result<ResponseJadwalHarianInstruktur>> = _resultJadwalHarian

    private val _resultHistoryById = MutableLiveData<Result<ResponseHistoryPerizinanById>>()
    val resultHistoryById: LiveData<Result<ResponseHistoryPerizinanById>> = _resultHistoryById

    private val _resultIzin = MutableLiveData<Result<ResponseInstruktur>>()
    val resultIzin: LiveData<Result<ResponseInstruktur>> = _resultIzin

    private suspend fun callJadwalHarianInstruktur(id: String) {
        try {
            _resultJadwalHarian.postValue(Result.Loading)
            val response = repository.getJadwalHarianInstruktur(id)
            _resultJadwalHarian.postValue(Result.Success(response))
        } catch (e: Exception) {
            _resultJadwalHarian.postValue(Result.Error(e.message.toString()))
        }
    }

    fun getJadwalHarianInstruktur(id: String) = viewModelScope.launch {
        callJadwalHarianInstruktur(id)
    }

    private suspend fun callHistoriPerizinanById(id: String) {
        try {
            _resultHistoryById.postValue(Result.Loading)
            val response = repository.getHistoriPerizinanById(id)
            _resultHistoryById.postValue(Result.Success(response))
        } catch (e: Exception) {
            _resultHistoryById.postValue(Result.Error(e.message.toString()))
        }
    }

    fun getHistoriPerizinanById(id: String) = viewModelScope.launch {
        callHistoriPerizinanById(id)
    }

    private suspend fun callIzin(idJadwalHarian: Int, idInstruktur: String) {
        try {
            _resultIzin.postValue(Result.Loading)
            val response = repository.izinInstruktur(idJadwalHarian, idInstruktur)
            _resultIzin.postValue(Result.Success(response))
        } catch (e: Exception) {
            _resultIzin.postValue(Result.Error(e.message.toString()))
        }
    }

    fun izinInstruktur(idJadwalHarian: Int, idInstruktur: String) = viewModelScope.launch {
        callIzin(idJadwalHarian, idInstruktur)
        callHistoriPerizinanById(idInstruktur)
    }
}