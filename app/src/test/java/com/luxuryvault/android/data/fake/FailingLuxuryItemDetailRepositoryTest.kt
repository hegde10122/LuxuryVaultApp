package com.luxuryvault.android.data.fake

import com.luxuryvault.android.core.util.Resource
import com.luxuryvault.android.domain.repository.LuxuryItemRepository
import com.luxuryvault.android.presentation.luxurydetails.LuxuryItemDetailUiModel
import com.luxuryvault.android.presentation.luxurylist.LuxuryItemUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FailingLuxuryItemDetailRepositoryTest : LuxuryItemRepository {
    override fun getLuxuryItems(): Flow<Resource<List<LuxuryItemUiModel>>> {
        throw UnsupportedOperationException()
    }

    override fun getLuxuryItemById(id: String): Flow<Resource<LuxuryItemDetailUiModel>> {
        return flow{emit(Resource.Error("Item not found")) }
    }
}