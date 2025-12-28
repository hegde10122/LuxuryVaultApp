package com.luxuryvault.android.data.fake

import com.luxuryvault.android.core.util.Resource
import com.luxuryvault.android.domain.repository.LuxuryItemRepository
import com.luxuryvault.android.presentation.luxurydetails.LuxuryItemDetailUiModel
import com.luxuryvault.android.presentation.luxurylist.LuxuryItemUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeLuxuryItemDetailRepositoryTest : LuxuryItemRepository {

    private val items = listOf(
        LuxuryItemDetailUiModel(
            id = "1",
            title = "Diamond Encrusted Watch",
            category = "Watches",
            subtitle = "Limited Edition",
            description = "An exquisite timepiece adorned with flawless diamonds, combining precision engineering with unparalleled luxury.",

            imageUrl = ""
        ),
        LuxuryItemDetailUiModel(
            id = "2",
            title = "Gold-Plated Supercar",
            category = "Automobiles",
            subtitle = "Custom Build",
            description = "Luxury car",
            imageUrl = ""
        ),
        LuxuryItemDetailUiModel(
            id = "3",
            title = "Private Island",
            category = "Real Estate",
            subtitle = "Maldives",
            description = "Luxury island",
            imageUrl = ""
        )
    )

    override fun getLuxuryItemById(id: String): Flow<Resource<LuxuryItemDetailUiModel>> {
        val item = items.find { it.id == id }
        return if (item != null) {
            flowOf(Resource.Success(item))
        } else {
            flowOf(Resource.Error("Item not found"))
        }
    }

    override fun getLuxuryItems(): Flow<Resource<List<LuxuryItemUiModel>>> {
        throw UnsupportedOperationException()
    }
}