package com.yusuf0080.manajementugas.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tugas")
data class Tugas(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val judul: String,
    val catatan: String,
    val tanggal: String,
    val Prioritas: String,
)
