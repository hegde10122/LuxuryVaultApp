package com.luxuryvault.android.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luxuryvault.android.domain.repository.LuxuryItemRepository
import com.luxuryvault.android.presentation.luxurylist.LuxuryItemsListViewModel

class LuxuryItemsListViewModelFactory(
    private val repository: LuxuryItemRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LuxuryItemsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LuxuryItemsListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}