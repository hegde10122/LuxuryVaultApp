package com.luxuryvault.android.presentation.luxurydetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luxuryvault.android.data.fake.FakeLuxuryItemDetailRepository
import com.luxuryvault.android.presentation.factory.LuxuryItemDetailViewModelFactory

@Composable
fun LuxuryItemDetailRoute(
    itemId: String,
    onBack: () -> Unit
) {
     val repository = FakeLuxuryItemDetailRepository()

    val viewModel : LuxuryItemDetailViewModel = viewModel(
        factory = LuxuryItemDetailViewModelFactory( repository)
    )

    val state by viewModel.uiState.collectAsState()


    LaunchedEffect(itemId) {
        viewModel.loadItem(itemId)
    }
    LuxuryItemDetailScreen(state = state, onBackClick = onBack)


}
