package com.luxuryvault.android.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luxuryvault.android.domain.repository.LuxuryItemRepository
import com.luxuryvault.android.presentation.luxurydetails.LuxuryItemDetailViewModel

class LuxuryItemDetailViewModelFactory(

    private val repository: LuxuryItemRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LuxuryItemDetailViewModel::class.java)) {
            return LuxuryItemDetailViewModel(

                repository = repository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
