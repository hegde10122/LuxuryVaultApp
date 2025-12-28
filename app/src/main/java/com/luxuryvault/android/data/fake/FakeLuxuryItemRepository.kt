package com.luxuryvault.android.data.fake
import com.luxuryvault.android.core.util.Resource
import com.luxuryvault.android.domain.repository.LuxuryItemRepository
import com.luxuryvault.android.presentation.luxurydetails.LuxuryItemDetailUiModel
import com.luxuryvault.android.presentation.luxurylist.LuxuryItemUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

/**
 * FakeLuxuryItemRepository is a controlled in-memory implementation of
 * LuxuryItemRepository that emits a predefined list of luxury items wrapped
 * in a Resource.Success state.
 *
 * This implementation is intentionally deterministic and side-effect free,
 * allowing the presentation layer and ViewModel logic to be exercised without
 * relying on database access, network calls, or asynchronous timing concerns.
 *
 * The repository exposes its data as a Flow to preserve the reactive contract
 * defined by the domain layer, ensuring that consumers interact with it in the
 * same manner as they would with a real production repository.
 *
 * This fake is primarily used during early development phases and ViewModel
 * testing to validate UI state transitions and data propagation while keeping
 * the system isolated from external dependencies.
 */

class FakeLuxuryItemRepository : LuxuryItemRepository {

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