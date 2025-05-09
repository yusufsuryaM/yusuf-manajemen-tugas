package com.yusuf0080.manajementugas.ui.screen

import android.content.res.Configuration
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.yusuf0080.manajementugas.R
import com.yusuf0080.manajementugas.model.Tugas
import com.yusuf0080.manajementugas.navigation.Screen
import com.yusuf0080.manajementugas.ui.theme.ManajemenTugasTheme
import com.yusuf0080.manajementugas.util.SettingsDataStore
import com.yusuf0080.manajementugas.util.SortPreferenceDataStore
import com.yusuf0080.manajementugas.util.ThemeDataStore
import com.yusuf0080.manajementugas.util.ViewModelFactory
import kotlinx.coroutines.launch

enum class SortCategory {
    JUDUL, WAKTU, PRIORITAS
}

enum class SortOrder {
    ASCENDING, DESCENDING
}

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val layoutDataStore = SettingsDataStore(context)
    val themeDataStore = ThemeDataStore(context)
    val sortPreferenceDataStore = SortPreferenceDataStore(context)

    val showList by layoutDataStore.layoutFlow.collectAsState(initial = true)
    val isDarkMode by themeDataStore.themeFlow.collectAsState(initial = false)
    val selectedSortCategory by sortPreferenceDataStore.sortCategoryFlow.collectAsState(initial = SortCategory.WAKTU)
    val sortOrder by sortPreferenceDataStore.sortOrderFlow.collectAsState(initial = SortOrder.DESCENDING)

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.app_name))
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    actions = {
                        IconButton(onClick = {
                            coroutineScope.launch {
                                themeDataStore.saveTheme(!isDarkMode)
                            }
                        }) {
                            Icon(
                                painter = painterResource(
                                    if (isDarkMode) R.drawable.baseline_light_mode_24
                                    else R.drawable.baseline_dark_mode_24
                                ),
                                contentDescription = stringResource(
                                    if (isDarkMode) R.string.light_mode
                                    else R.string.dark_mode
                                ),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }

                        IconButton(onClick = {
                            coroutineScope.launch {
                                layoutDataStore.saveLayout(!showList)
                            }
                        }) {
                            Icon(
                                painter = painterResource(
                                    if (showList) R.drawable.baseline_view_list_24
                                    else R.drawable.baseline_grid_view_24
                                ),
                                contentDescription = stringResource(
                                    if (showList) R.string.grid
                                    else R.string.list
                                ),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                FilterChip(
                                    selected = selectedSortCategory == SortCategory.JUDUL,
                                    onClick = {
                                        coroutineScope.launch {
                                            sortPreferenceDataStore.saveSortCategory(SortCategory.JUDUL)
                                        }
                                    },
                                    label = { Text("Judul") }
                                )
                                FilterChip(
                                    selected = selectedSortCategory == SortCategory.WAKTU,
                                    onClick = {
                                        coroutineScope.launch {
                                            sortPreferenceDataStore.saveSortCategory(SortCategory.WAKTU)
                                        }
                                    },
                                    label = { Text("Waktu") }
                                )
                                FilterChip(
                                    selected = selectedSortCategory == SortCategory.PRIORITAS,
                                    onClick = {
                                        coroutineScope.launch {
                                            sortPreferenceDataStore.saveSortCategory(SortCategory.PRIORITAS)
                                        }
                                    },
                                    label = { Text("Prioritas") }
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                FilterChip(
                                    selected = sortOrder == SortOrder.ASCENDING,
                                    onClick = {
                                        coroutineScope.launch {
                                            sortPreferenceDataStore.saveSortOrder(SortOrder.ASCENDING)
                                        }
                                    },
                                    label = { Text("↑") }
                                )
                                FilterChip(
                                    selected = sortOrder == SortOrder.DESCENDING,
                                    onClick = {
                                        coroutineScope.launch {
                                            sortPreferenceDataStore.saveSortOrder(SortOrder.DESCENDING)
                                        }
                                    },
                                    label = { Text("↓") }
                                )
                            }
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.FormBaru.route)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.tambah_catatan),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    ) { innerPadding ->
        ScreenContent(
            showList = showList,
            sortCategory = selectedSortCategory,
            sortOrder = sortOrder,
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ScreenContent(
    showList: Boolean,
    sortCategory: SortCategory,
    sortOrder: SortOrder,
    modifier: Modifier,
    navController: NavController
) {
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: MainViewModel = viewModel(factory = factory)
    val data by viewModel.data.collectAsState()

    val sortedData = when (sortCategory) {
        SortCategory.JUDUL -> {
            if (sortOrder == SortOrder.ASCENDING) {
                data.sortedBy { it.judul }
            } else {
                data.sortedByDescending { it.judul }
            }
        }
        SortCategory.WAKTU -> {
            if (sortOrder == SortOrder.ASCENDING) {
                data.sortedBy { it.tanggal }
            } else {
                data.sortedByDescending { it.tanggal }
            }
        }
        SortCategory.PRIORITAS -> {
            val comparator = compareBy<Tugas> {
                when (it.Prioritas) {
                    "High" -> 0
                    "Medium" -> 1
                    "Low" -> 2
                    else -> 3
                }
            }
            if (sortOrder == SortOrder.ASCENDING) {
                data.sortedWith(comparator.reversed())
            } else {
                data.sortedWith(comparator)
            }
        }
    }

    if (sortedData.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.list_kosong))
        }
    }
    else {
        if (showList) {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 84.dp)
            ) {
                items(sortedData) {
                    ListItem(tugas = it) {
                        navController.navigate(Screen.FormUbah.withId(it.id))
                    }
                    HorizontalDivider()
                }
            }
        }
        else {
            LazyVerticalStaggeredGrid(
                modifier = modifier.fillMaxSize(),
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(8.dp, 8.dp, 8.dp, 84.dp)
            ) {
                items(sortedData) {
                    GridItem(tugas = it) {
                        navController.navigate(Screen.FormUbah.withId(it.id))
                    }
                }
            }
        }
    }
}

@Composable
fun ListItem(tugas: Tugas, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .clickable { onClick() }
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = tugas.judul,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = tugas.catatan,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(text = tugas.tanggal)

        Text(
            text = tugas.Prioritas,
            color = when (tugas.Prioritas) {
                "High" -> MaterialTheme.colorScheme.error
                "Medium" -> MaterialTheme.colorScheme.tertiary
                "Low" -> MaterialTheme.colorScheme.primary
                else -> MaterialTheme.colorScheme.onSurface
            }
        )
    }
}

@Composable
fun GridItem(tugas: Tugas, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, DividerDefaults.color)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = tugas.judul,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = tugas.catatan,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis
            )
            Text(text = tugas.tanggal)

            Text(
                text = tugas.Prioritas,
                color = when (tugas.Prioritas) {
                    "High" -> MaterialTheme.colorScheme.error
                    "Medium" -> MaterialTheme.colorScheme.tertiary
                    "Low" -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.onSurface
                }
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    ManajemenTugasTheme {
        MainScreen(rememberNavController())
    }
}