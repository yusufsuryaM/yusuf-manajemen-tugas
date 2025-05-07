package com.yusuf0080.manajementugas.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yusuf0080.manajementugas.database.TugasDao
import com.yusuf0080.manajementugas.model.Tugas
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class MainViewModel(dao: TugasDao) : ViewModel() {

    val data: StateFlow<List<Tugas>> = dao.getTugas().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )
}