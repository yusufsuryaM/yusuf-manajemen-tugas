package com.yusuf0080.manajementugas.ui.screen

import androidx.lifecycle.ViewModel
import com.yusuf0080.manajementugas.model.Tugas

class MainViewModel : ViewModel() {

    val data = listOf(
        Tugas(
            1,
            "Mengerjakan Assessment 2 Mobpro",
            "Membuat aplikasi pencatatan prioritas tugas",
            "2025-05-03 11:11:11",
            "High"
        ),
        Tugas(
            2,
            "Mengerjakan Tugas KAT",
            "Mengumpulkan data",
            "2025-05-03 12:05:10",
            "Medium"
        ),
        Tugas(
            3,
            "Membuat data sememtara",
            "data sementara",
            "2025-05-03 11:11:11",
            "Low"
        ),
        Tugas(
            1,
            "Mengerjakan Assessment 2 Mobpro",
            "Membuat aplikasi pencatatan prioritas tugas",
            "2025-05-03 11:11:11",
            "High"
        ),
        Tugas(
            2,
            "Mengerjakan Tugas KAT",
            "Mengumpulkan data",
            "2025-05-03 12:05:10",
            "Medium"
        ),
        Tugas(
            3,
            "Membuat data sememtara",
            "data sementara",
            "2025-05-03 11:11:11",
            "Low"
        ),
        Tugas(
            1,
            "Mengerjakan Assessment 2 Mobpro",
            "Membuat aplikasi pencatatan prioritas tugas",
            "2025-05-03 11:11:11",
            "High"
        ),
        Tugas(
            2,
            "Mengerjakan Tugas KAT",
            "Mengumpulkan data",
            "2025-05-03 12:05:10",
            "Medium"
        ),
        Tugas(
            3,
            "Membuat data sememtara",
            "data sementara",
            "2025-05-03 11:11:11",
            "Low"
        )
    )
    fun getCatatan(id: Long): Tugas? {
        return data.find { it.id == id }
    }
}