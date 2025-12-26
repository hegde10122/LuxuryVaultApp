package com.luxuryvault.android.presentation.luxurylist

data class LuxuryItemListUiState(

    val isLoading:Boolean = false,
    val items: List<LuxuryItemUiModel> = emptyList(),
    val errorMessage:String?=null
)

/*
Mental model:
If this object changes → the screen redraws.
* */

