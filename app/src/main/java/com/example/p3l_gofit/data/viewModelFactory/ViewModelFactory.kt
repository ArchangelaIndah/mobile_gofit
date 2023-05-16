package com.example.p3l_gofit.data.viewModelFactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.p3l_gofit.data.di.Injection
import com.example.p3l_gofit.data.repo.UsersRepository
import com.example.p3l_gofit.ui.activity.home.homeInstruktur.HomeInstrukturViewModel
import com.example.p3l_gofit.ui.activity.login.LoginViewModel

class ViewModelFactory private constructor(
    private val usersRepository: UsersRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(usersRepository) as T
        }else if(modelClass.isAssignableFrom(HomeInstrukturViewModel::class.java)) {
            return HomeInstrukturViewModel(usersRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}