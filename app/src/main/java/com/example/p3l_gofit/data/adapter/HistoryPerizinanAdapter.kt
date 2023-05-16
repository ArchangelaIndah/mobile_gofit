package com.example.p3l_gofit.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.p3l_gofit.data.api.model.DataItem
import com.example.p3l_gofit.data.api.model.DataItem2
import com.example.p3l_gofit.databinding.ItemRowHistoryperizinanBinding
import com.example.p3l_gofit.databinding.ItemRowJadwalharianBinding

class HistoryPerizinanAdapter(private val historyPerizinan: List<DataItem2>) :
    RecyclerView.Adapter<HistoryPerizinanAdapter.ListViewHolder>() {

    class ListViewHolder(var binding: ItemRowHistoryperizinanBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowHistoryperizinanBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val namaKelas =
            historyPerizinan[position].fJadwalHarian.FJadwalUmum.fBookingKelas?.namaKelas
        val sesiKelas = historyPerizinan[position].fJadwalHarian.FJadwalUmum.sesiKelas
        val tanggalKelas = historyPerizinan[position].fJadwalHarian.tanggalJadwalHarian
        val tanggalIzin = historyPerizinan[position].tanggalIzin
        val instrukturPengganti = historyPerizinan[position].FInstrukturPengganti?.namaInstruktur

        holder.binding.apply {
            namaKelasHistori.text = namaKelas
            sesiKelasHistory.text = "Sesi kelas : $sesiKelas"
            tanggalKelasHistori.text = "Tanggal kelas : $tanggalKelas"
            tanggalIzinHistori.text = "Tanggal izin : $tanggalIzin"
            instrukturPenggantiHistori.text = "Instruktur pengganti : ${instrukturPengganti ?: "-"}"

            if (historyPerizinan[position].status == 0) {
                cardStatus.background.setTint(0xFFFFA458.toInt())
                statusIzin.text = "Waiting"
            } else if (historyPerizinan[position].status == 1) {
                cardStatus.background.setTint(0xFF2ECC71.toInt())
                statusIzin.text = "Approved"
            } else {
                cardStatus.background.setTint(0xFFE74C3C.toInt())
                statusIzin.text = "Rejected"
            }


        }
    }

    override fun getItemCount(): Int = historyPerizinan.size

}