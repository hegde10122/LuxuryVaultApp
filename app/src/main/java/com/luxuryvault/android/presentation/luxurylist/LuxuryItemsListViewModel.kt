package com.luxuryvault.android.presentation.luxurylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luxuryvault.android.core.util.Resource
import com.luxuryvault.android.domain.repository.LuxuryItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * LuxuryItemsListViewModel is the presentation-layer orchestrator for the
 * LuxuryItemsListScreen. Its responsibility is to expose a stable, immutable
 * UI state that represents what the list screen should render at any given
 * moment, while remaining completely agnostic of where the underlying data
 * originates from.
 *
 * This ViewModel does not contain business rules and does not perform any
 * domain-level validation. Instead, it coordinates asynchronous data retrieval
 * by interacting with a LuxuryItemRepository abstraction and translating the
 * resulting operation states into a screen-specific UI model that is safe
 * and convenient for Jetpack Compose to consume.
 *
 * Internally, the repository emits a stream of Resource<List<LuxuryItem>>,
 * where Resource represents the state of the data-fetching operation
 * (loading, success, or error). The ViewModel collects this stream within
 * its lifecycle-aware coroutine scope and maps each Resource state into a
 * LuxuryItemsListUiState. This mapping ensures that Compose UI elements are
 * never exposed to data-layer or domain-layer concepts such as loading
 * semantics, error propagation strategies, or asynchronous control flow.
 *
 * The ViewModel owns the single source of truth for the screen by maintaining
 * a StateFlow of LuxuryItemsListUiState. Any change in data availability,
 * loading status, or failure condition results in a new immutable state
 * emission, which in turn triggers recomposition in the UI layer.
 *
 * This ViewModel is intentionally designed without any Android framework
 * dependencies beyond the ViewModel itself. It does not reference Room,
 * Retrofit, Hilt, or navigation components. This design allows the ViewModel
 * to be tested using fast, deterministic JVM unit tests and ensures that
 * future integration of concrete data sources or dependency injection
 * frameworks does not require refactoring of presentation logic.
 *
 * In the overall Clean Architecture flow, this ViewModel acts as an adapter
 * between the domain-facing repository contract and the UI-facing composables.
 * It enables the screen to remain a dumb renderer of state while preserving
 * clear separation of concerns and long-term maintainability.
 */

@HiltViewModel
class LuxuryItemsListViewModel @Inject constructor(
    private val repository: LuxuryItemRepository
) : ViewModel() {

    private val _state =
        MutableStateFlow(LuxuryItemListUiState(isLoading = true))
    val state: StateFlow<LuxuryItemListUiState> = _state

    init {
        loadItems()
    }

    private fun loadItems() {
        viewModelScope.launch {
            repository.getLuxuryItems()
                .onStart {
                    _state.update { it.copy(isLoading = true, errorMessage = null) }
                }
                .catch { throwable ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.message ?: "Unknown error"
                        )
                    }
                }
                .collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            _state.update {
                                it.copy(
                                    isLoading = true,
                                    errorMessage = null
                                )
                            }
                        }

                        is Resource.Success -> {
                            val items = resource.data.orEmpty()
                            _state.update {
                                it.copy(
                                    items = items,
                                    isLoading = false,
                                    errorMessage = null
                                )
                            }
                        }

                        is Resource.Error -> {
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    errorMessage = resource.message ?: "Unknown error"
                                )
                            }
                        }


                    }
                }
        }
    }

}

/**
 * Responsibility
 *
 * Fetch list of luxury items
   Convert Resource<List<>> → UI State
   Expose stable state for Compose
   No navigation
   No Android UI logic
   No data source knowledge
 */
