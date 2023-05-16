package com.example.p3l_gofit.ui.activity.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.p3l_gofit.data.repo.Result
import com.example.p3l_gofit.data.viewModelFactory.ViewModelFactory
import com.example.p3l_gofit.databinding.ActivityLoginBinding
import com.example.p3l_gofit.ui.activity.home.homeInstruktur.HomeInstrukturActivity
import com.example.p3l_gofit.ui.activity.home.homeMO.HomeMOActivity
import com.example.p3l_gofit.ui.activity.home.homeMember.HomeMemberActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var sharedPreferences: SharedPreferences? = null
    private val myPreference = "myPrefLogin"
    private val idKey = "idKey"
    private val nameKey = "nameKey"

    private val loginViewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(myPreference, Context.MODE_PRIVATE)

        binding.radioBtnMember.isChecked = true

        loginViewModel.loginResultInstruktur.observe(this){
            when (it){
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    saveSharedPreferences(it.data.user.id, it.data.user.namaInstruktur)
                    moveToHomeInstruktur()

                }
                is Result.Error -> {
                    showLoading(false)
                    toastMaker(it.error)
                }
            }
        }

        loginViewModel.loginResultMember.observe(this){
            when (it){
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    saveSharedPreferences(it.data.user.id, it.data.user.nama)
                    moveToHomeMember()
                }
                is Result.Error -> {
                    showLoading(false)
                    toastMaker(it.error)
                }
            }
        }

        loginViewModel.loginResultPegawai.observe(this){
            when (it){
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    saveSharedPreferences(it.data.user.id.toString(), it.data.user.namaPegawai)
                    moveToHomeMO()
                }
                is Result.Error -> {
                    showLoading(false)
                    toastMaker(it.error)
                }
            }
        }

        binding.buttonLogin.setOnClickListener {
            binding.apply {
                val username = edRegisterUsername.text.toString()
                val password = edRegisterPassword.text.toString()
                val valid = username.isNotEmpty() && password.isNotEmpty()

                if(valid){
                    if(radioBtnMember.isChecked){
                        loginViewModel.loginMember(username, password)
                    }else if(radioBtnInstruktur.isChecked){
                        loginViewModel.loginInstruktur(username, password)
                    }else{
                        loginViewModel.loginPegawai(username, password)
                    }

                }
                else toastMaker("Please fill all the fields")

            }
        }
    }

    private fun saveSharedPreferences(id: String, name: String){
        val openSharedPreferences = sharedPreferences!!.edit()
        openSharedPreferences.putString(idKey, id)
        openSharedPreferences.putString(nameKey, name)
        openSharedPreferences.apply()
    }

    private fun moveToHomeInstruktur(){
        startActivity(Intent(this, HomeInstrukturActivity::class.java))
    }

    private fun moveToHomeMember(){
        startActivity(Intent(this, HomeMemberActivity::class.java))
    }

    private fun moveToHomeMO(){
        startActivity(Intent(this, HomeMOActivity::class.java))
    }

    private fun showLoading(isLoading: Boolean) {
        binding.layoutLoading.layoutAllLoading.visibility =
            if (isLoading) View.VISIBLE else View.GONE
    }

    private fun toastMaker(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


}