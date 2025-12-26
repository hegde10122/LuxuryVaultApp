package com.luxuryvault.android.domain.repository

import com.luxuryvault.android.core.util.Resource
import com.luxuryvault.android.presentation.luxurylist.LuxuryItemUiModel
import kotlinx.coroutines.flow.Flow


interface LuxuryItemRepository {
    fun getLuxuryItems(): Flow<Resource<List<LuxuryItemUiModel>>>
}
