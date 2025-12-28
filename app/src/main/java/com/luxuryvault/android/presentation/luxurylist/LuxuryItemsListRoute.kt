package com.luxuryvault.android.presentation.luxurylist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luxuryvault.android.data.fake.FakeLuxuryItemRepository
import com.luxuryvault.android.presentation.factory.LuxuryItemsListViewModelFactory

@Composable
fun LuxuryItemsListRoute(
    onItemSelected: (String) -> Unit,
    onAddClick: () -> Unit
) {

    /*
    * NOTE:
    * FakeLuxuryItemRepository is intentionally used in Phase-02
    * to keep ViewModel wiring explicit and test-friendly.
    * This will be replaced by Hilt-provided implementation in Phase-03
    * without changing this Route’s public API.
    */

    val repository = FakeLuxuryItemRepository()

    val viewModel: LuxuryItemsListViewModel = viewModel(
        factory = LuxuryItemsListViewModelFactory(repository)
    )

    val state by viewModel.state.collectAsState()

    LuxuryItemsListScreen(
        state = state,
        onItemClick = {item -> onItemSelected(item.id)},
        onAddClick = onAddClick
    )
}