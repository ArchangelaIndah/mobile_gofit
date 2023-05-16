package com.example.p3l_gofit.ui.activity.home.homeInstruktur

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.p3l_gofit.R
import com.example.p3l_gofit.data.adapter.HistoryPerizinanAdapter
import com.example.p3l_gofit.data.repo.Result
import com.example.p3l_gofit.data.adapter.JadwalHarianAdapter
import com.example.p3l_gofit.data.api.model.DataItem
import com.example.p3l_gofit.data.api.model.DataItem2
import com.example.p3l_gofit.data.viewModelFactory.ViewModelFactory
import com.example.p3l_gofit.databinding.ActivityHomeInstrukturBinding
import com.example.p3l_gofit.ui.activity.login.LoginViewModel

class HomeInstrukturActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeInstrukturBinding
    private var sharedPreferences: SharedPreferences? = null
    private val myPreference = "myPrefLogin"
    private val idKey = "idKey"
    private val nameKey = "nameKey"
    private var id = ""
    private var name = ""

    private val homeInstrukturViewModel by viewModels<HomeInstrukturViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeInstrukturBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getSharedPreferences(myPreference, Context.MODE_PRIVATE)
        id = sharedPreferences?.getString(idKey, "").toString()
        name = sharedPreferences?.getString(nameKey, "").toString()

        binding.topAppBar.title = "Halo, $name"

        homeInstrukturViewModel.getJadwalHarianInstruktur(id)

        homeInstrukturViewModel.resultJadwalHarian.observe(this) {
            when (it) {
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    if (it.data.data.isNullOrEmpty()) {
                        toastMaker("Tidak ada data")
                    } else {
                        binding.topAppBar.subtitle = "Instruktur - Jadwal Harian"
                        setJadwalHarianAdapter(it.data.data)
                    }
                }
                is Result.Error -> {
                    showLoading(false)
                    toastMaker(it.error)
                }
            }
        }

        homeInstrukturViewModel.resultHistoryById.observe(this) {
            when (it) {
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    if(it.data.data.isNullOrEmpty()) {
                        toastMaker("Tidak ada data")
                    }else{
                        binding.topAppBar.subtitle = "Instruktur - Histori Perizinan"
                        setHistoryPerizinanByIdAdapter(it.data.data)
                    }

                }
                is Result.Error -> {
                    showLoading(false)
                    toastMaker(it.error)
                }
            }
        }

        homeInstrukturViewModel.resultIzin.observe(this) {
            when (it) {
                is Result.Loading -> {
                    showLoading(true)
                }
                is Result.Success -> {
                    showLoading(false)
                    toastMaker("Berhasil mengajukan izin")
                }
                is Result.Error -> {
                    showLoading(false)
                    toastMaker(it.error)
                }
            }
        }



        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.jadwalHarian -> {

                    homeInstrukturViewModel.getJadwalHarianInstruktur(id)
                    true
                }
                R.id.historiPerizinan -> {

                    homeInstrukturViewModel.getHistoriPerizinanById(id)
                    true
                }

                else -> false
            }
        }
    }

    private fun setJadwalHarianAdapter(jadwalHarian: List<DataItem>) {
        val adapter = JadwalHarianAdapter(jadwalHarian, onClick = { dataItem ->
            homeInstrukturViewModel.izinInstruktur(dataItem.id, id)
        })
        binding.rvJadwalHarian.layoutManager = LinearLayoutManager(this)
        binding.rvJadwalHarian.adapter = adapter
    }

    private fun setHistoryPerizinanByIdAdapter(perizinan: List<DataItem2>) {

            val adapter = HistoryPerizinanAdapter(perizinan)
            binding.rvJadwalHarian.layoutManager = LinearLayoutManager(this)
            binding.rvJadwalHarian.adapter = adapter


    }

    private fun showLoading(isLoading: Boolean) {
        binding.layoutLoading.layoutAllLoading.visibility =
            if (isLoading) View.VISIBLE else View.GONE
    }

    private fun toastMaker(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}