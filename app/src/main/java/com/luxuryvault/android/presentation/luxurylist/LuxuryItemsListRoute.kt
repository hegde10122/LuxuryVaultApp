package com.luxuryvault.android.presentation.luxurylist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luxuryvault.android.data.fake.FakeLuxuryItemRepository
import com.luxuryvault.android.presentation.LuxuryItemsListViewModelFactory

@Composable
fun LuxuryItemsListRoute(
    onItemSelected: (LuxuryItemUiModel) -> Unit,
    onAddClick: () -> Unit
) {
    val repository = FakeLuxuryItemRepository()

    val viewModel: LuxuryItemsListViewModel = viewModel(
        factory = LuxuryItemsListViewModelFactory(repository)
    )

    val state by viewModel.state.collectAsState()

    LuxuryItemsListScreen(
        state = state,
        onItemClick = onItemSelected,
        onAddClick = onAddClick
    )
}