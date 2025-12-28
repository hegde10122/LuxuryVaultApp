package com.luxuryvault.android.data.fake

import com.luxuryvault.android.core.util.Resource
import com.luxuryvault.android.domain.repository.LuxuryItemRepository
import com.luxuryvault.android.presentation.luxurydetails.LuxuryItemDetailUiModel
import com.luxuryvault.android.presentation.luxurylist.LuxuryItemUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


/**
 * Test-only fake implementation of [com.luxuryvault.android.domain.repository.LuxuryItemRepository].
 *
 * WHY THIS EXISTS:
 * - Provides deterministic data for unit tests
 * - Avoids Room / Retrofit / network dependencies
 * - Allows ViewModels to be tested in isolation
 *
 * IMPORTANT:
 * - Must NEVER live in src/main
 * - Must NEVER be referenced by production Hilt modules
 */
class FakeLuxuryItemRepositoryTest : LuxuryItemRepository {
    override fun getLuxuryItems(): Flow<Resource<List<LuxuryItemUiModel>>> {

        return flowOf(
            Resource.Success(
                listOf(
                    LuxuryItemUiModel(
                        id = "1",
                        title = "Diamond Encrusted Watch",
                        category = "Watches",
                        subtitle = "Limited Edition",
                        imageUrl = ""
                    ),
                    LuxuryItemUiModel(
                        id = "2",
                        title = "Gold-Plated Supercar",
                        category = "Automobiles",
                        subtitle = "Custom Build",
                        imageUrl = ""
                    ),
                    LuxuryItemUiModel(
                        id = "3",
                        title = "Private Island",
                        category = "Real Estate",
                        subtitle = "Maldives",
                        imageUrl = ""
                    )
                )))

    }

    override fun getLuxuryItemById(id: String): Flow<Resource<LuxuryItemDetailUiModel>> {
        throw UnsupportedOperationException()
    }


}