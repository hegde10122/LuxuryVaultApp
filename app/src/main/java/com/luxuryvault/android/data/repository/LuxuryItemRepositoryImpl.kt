package com.luxuryvault.android.data.repository

import com.luxuryvault.android.core.util.Resource
import com.luxuryvault.android.domain.repository.LuxuryItemRepository
import com.luxuryvault.android.presentation.luxurydetails.LuxuryItemDetailUiModel
import com.luxuryvault.android.presentation.luxurylist.LuxuryItemUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Production repository implementation.
 *
 * Talks to:
 * - Room DAO
 * - Network API
 * - Cache
 */
@Singleton
class LuxuryItemRepositoryImpl @Inject constructor() : LuxuryItemRepository

{
    override fun getLuxuryItems(): Flow<Resource<List<LuxuryItemUiModel>>> {
        return flowOf(Resource.Success(emptyList()))
    }

    override fun getLuxuryItemById(id: String): Flow<Resource<LuxuryItemDetailUiModel>> {
        return flowOf(Resource.Error("Not implemented"))
    }

}