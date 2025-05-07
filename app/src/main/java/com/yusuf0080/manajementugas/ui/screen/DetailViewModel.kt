package com.yusuf0080.manajementugas.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuf0080.manajementugas.database.TugasDao
import com.yusuf0080.manajementugas.model.Tugas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DetailViewModel(private val dao: TugasDao) : ViewModel() {

    private val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    fun insert(judul: String, isi: String, prioritas: String) {
        val tugas = Tugas(
            tanggal = formatter.format(Date()),
            judul = judul,
            catatan = isi,
            Prioritas = prioritas
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(tugas)
        }
    }

    suspend fun getTugas(id: Long): Tugas? {
        return dao.getTugasById(id)
    }

    fun update(id: Long, judul: String, isi: String, prioritas: String) {
        val tugas = Tugas(
            id = id,
            tanggal = formatter.format(Date()),
            judul = judul,
            catatan = isi,
            Prioritas = prioritas
        )

        viewModelScope.launch(Dispatchers.IO) {
            dao.update(tugas)
        }
    }
    fun delete(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}