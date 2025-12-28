package com.luxuryvault.android.data.fake

import com.luxuryvault.android.core.util.Resource
import com.luxuryvault.android.domain.repository.LuxuryItemRepository
import com.luxuryvault.android.presentation.luxurydetails.LuxuryItemDetailUiModel
import com.luxuryvault.android.presentation.luxurylist.LuxuryItemUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FailingLuxuryItemRepositoryTest : LuxuryItemRepository {
    override fun getLuxuryItems(): Flow<Resource<List<LuxuryItemUiModel>>> =
        flow {
            throw RuntimeException("Database failure")
        }

    override fun getLuxuryItemById(id: String): Flow<Resource<LuxuryItemDetailUiModel>> {
        throw UnsupportedOperationException()
    }
}