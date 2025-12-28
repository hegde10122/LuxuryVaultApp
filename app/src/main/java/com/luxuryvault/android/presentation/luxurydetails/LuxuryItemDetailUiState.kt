package com.luxuryvault.android.presentation.luxurydetails

data class LuxuryItemDetailUiState(
    val item: LuxuryItemDetailUiModel? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null

)
