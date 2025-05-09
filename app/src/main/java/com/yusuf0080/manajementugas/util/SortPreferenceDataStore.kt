package com.yusuf0080.manajementugas.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.yusuf0080.manajementugas.ui.screen.SortCategory
import com.yusuf0080.manajementugas.ui.screen.SortOrder

class SortPreferenceDataStore(private val context: Context) {

    companion object {
        private val SORT_CATEGORY = intPreferencesKey("sort_category")
        private val SORT_ORDER = intPreferencesKey("sort_order")
    }

    val sortCategoryFlow: Flow<SortCategory> = context.dataStore.data.map { preferences ->
        val ordinal = preferences[SORT_CATEGORY] ?: SortCategory.WAKTU.ordinal
        SortCategory.entries[ordinal]
    }

    val sortOrderFlow: Flow<SortOrder> = context.dataStore.data.map { preferences ->
        val ordinal = preferences[SORT_ORDER] ?: SortOrder.DESCENDING.ordinal
        SortOrder.entries[ordinal]
    }

    suspend fun saveSortCategory(category: SortCategory) {
        context.dataStore.edit { preferences ->
            preferences[SORT_CATEGORY] = category.ordinal
        }
    }

    suspend fun saveSortOrder(order: SortOrder) {
        context.dataStore.edit { preferences ->
            preferences[SORT_ORDER] = order.ordinal
        }
    }
}