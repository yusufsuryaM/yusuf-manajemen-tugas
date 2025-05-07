package com.yusuf0080.manajementugas.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.yusuf0080.manajementugas.model.Tugas
import kotlinx.coroutines.flow.Flow

@Dao
interface TugasDao {

    @Insert
    suspend fun insert(tugas: Tugas)

    @Update
    suspend fun update(tugas: Tugas)

    @Query("SELECT * FROM tugas ORDER BY tanggal DESC")
    fun getTugas(): Flow<List<Tugas>>
}