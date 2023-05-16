package com.example.p3l_gofit.data.adapter

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.p3l_gofit.data.api.model.DataItem
import com.example.p3l_gofit.databinding.ItemRowJadwalharianBinding

class JadwalHarianAdapter(private val jadwalHarian : List<DataItem>, private val onClick : (DataItem) -> Unit ) :  RecyclerView.Adapter<JadwalHarianAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemRowJadwalharianBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowJadwalharianBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val namaKelas = jadwalHarian[position].fJadwalUmum?.fBookingKelas?.namaKelas
        val tanggal = jadwalHarian[position].tanggalJadwalHarian
        val sesiKelas = jadwalHarian[position].fJadwalUmum?.sesiKelas
        holder.binding.namaKelas.text = namaKelas
        holder.binding.tanggal.text = "Tanggal kelas : $tanggal"
        holder.binding.sesiKelas.text = "Sesi kelas : $sesiKelas"

        holder.binding.btnAjukan.setOnClickListener {  onClick(jadwalHarian[position]) }

    }

    override fun getItemCount(): Int = jadwalHarian.size


}