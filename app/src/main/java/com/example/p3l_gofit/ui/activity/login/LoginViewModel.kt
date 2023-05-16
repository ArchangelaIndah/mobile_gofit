package com.example.p3l_gofit.ui.activity.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p3l_gofit.data.api.model.ResponseInstruktur
import com.example.p3l_gofit.data.api.model.ResponseMember
import com.example.p3l_gofit.data.api.model.ResponsePegawai
import com.example.p3l_gofit.data.repo.UsersRepository
import com.example.p3l_gofit.data.repo.Result
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.HttpException

class LoginViewModel (private val usersRepository: UsersRepository) : ViewModel() {

    private val _loginResultInstruktur = MutableLiveData<Result<ResponseInstruktur>>()
    val loginResultInstruktur: LiveData<Result<ResponseInstruktur>> = _loginResultInstruktur

    private val _loginResultMember = MutableLiveData<Result<ResponseMember>>()
    val loginResultMember: LiveData<Result<ResponseMember>> = _loginResultMember

    private val _loginResultPegawai = MutableLiveData<Result<ResponsePegawai>>()
    val loginResultPegawai: LiveData<Result<ResponsePegawai>> = _loginResultPegawai

    private suspend fun callLoginInstruktur(email: String, password: String) {
        try {
            _loginResultInstruktur.postValue(Result.Loading)
            val response = usersRepository.loginInstruktur(email, password)
            _loginResultInstruktur.postValue(Result.Success(response))
        } catch (e: HttpException) {
            if (e.code() == 401) {
                val error = e.response()?.errorBody()?.string()?.let { JSONObject(it) }
                _loginResultInstruktur.postValue(error?.getString("message")?.let { Result.Error(it) })
            } else _loginResultInstruktur.postValue(Result.Error(e.message().toString()))
        } catch (e: Exception) {
            _loginResultInstruktur.postValue(Result.Error(e.message.toString()))
        }
    }

    fun loginInstruktur(email: String, password: String) = viewModelScope.launch {
        callLoginInstruktur(email, password)
    }

    private suspend fun callLoginMember( id: String, password: String) {
        try {
            _loginResultMember.postValue(Result.Loading)
            val response = usersRepository.loginMember(id, password)
            _loginResultMember.postValue(Result.Success(response))
        } catch (e: HttpException) {
            if (e.code() == 401) {
                val error = e.response()?.errorBody()?.string()?.let { JSONObject(it) }
                _loginResultMember.postValue(error?.getString("message")?.let { Result.Error(it) })
            } else _loginResultMember.postValue(Result.Error(e.message().toString()))
        } catch (e: Exception) {
            _loginResultMember.postValue(Result.Error(e.message.toString()))
        }
    }

    fun loginMember(id: String, password: String) = viewModelScope.launch {
        callLoginMember(id, password)
    }

    private suspend fun callLoginPegawai(namaPegawai: String, password: String) {
        try {
            _loginResultPegawai.postValue(Result.Loading)
            val response = usersRepository.loginPegawai(namaPegawai, password)
            if(response.user?.role == "MO"){
                _loginResultPegawai.postValue(Result.Success(response))
            }else{
                _loginResultPegawai.postValue(Result.Error("Anda bukan Manager Operasional"))
            }
        } catch (e: HttpException) {
            if (e.code() == 401) {
                val error = e.response()?.errorBody()?.string()?.let { JSONObject(it) }
                _loginResultPegawai.postValue(error?.getString("message")?.let { Result.Error(it) })
            } else _loginResultPegawai.postValue(Result.Error(e.message().toString()))
        } catch (e: Exception) {
            _loginResultPegawai.postValue(Result.Error(e.message.toString()))
        }
    }

    fun loginPegawai(namaPegawai: String, password: String) = viewModelScope.launch {
        callLoginPegawai(namaPegawai, password)
    }

}