package com.luxuryvault.android.presentation.luxurydetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luxuryvault.android.core.util.Resource
import com.luxuryvault.android.domain.repository.LuxuryItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LuxuryItemDetailViewModel @Inject constructor(
    private val repository: LuxuryItemRepository) : ViewModel() {

    private val _state = MutableStateFlow(LuxuryItemDetailUiState(isLoading = true))
    val uiState : StateFlow<LuxuryItemDetailUiState> = _state


    fun loadItem(itemId: String) {

        viewModelScope.launch {
            repository.getLuxuryItemById(itemId).onStart {
                _state.update{
                    it.copy(isLoading = true, errorMessage = null)
                }
            }
                .catch {
                    throwable -> _state.update {
                        it.copy(isLoading = false, errorMessage = throwable.message ?: "Unknown Error")
                }
                }.collect {
                    resource ->
                    when(resource) {

                        is Resource.Loading -> {
                            _state.update {it.copy(isLoading = true )}
                        }

                        is Resource.Success -> {
                            _state.update {it.copy(isLoading = false, errorMessage = null, item = resource.data )}
                        }

                        is Resource.Error -> {
                            _state.update {it.copy(isLoading = false, errorMessage = resource.message )}
                        }
                    }
                }
        }
    }
}

/**
 * This ViewModel backs LuxuryItemDetailScreen
 *
 * Responsibility

 * What this VM does----
   Loads one luxury item by id
   Exposes a stable UI state
   Converts Resource<T> → UiState
   Handles loading + error
 *
 * What it does NOT do
   No navigation
   No validation
   No formatting
   No Android UI logic
 */

