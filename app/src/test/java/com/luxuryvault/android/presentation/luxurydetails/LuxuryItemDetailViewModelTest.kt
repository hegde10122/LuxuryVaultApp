package com.luxuryvault.android.presentation.luxurydetails

import android.util.Log
import com.luxuryvault.android.data.fake.FailingLuxuryItemDetailRepository
import com.luxuryvault.android.data.fake.FakeLuxuryItemDetailRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LuxuryItemDetailViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: LuxuryItemDetailViewModel
    private lateinit var repository: FakeLuxuryItemDetailRepository

    private lateinit var repository2: FailingLuxuryItemDetailRepository

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    // Test 1 : Initial loading state
    @Test
    fun `initial loading state`() = runTest {

        repository = FakeLuxuryItemDetailRepository()
        viewModel = LuxuryItemDetailViewModel( repository)

        val state = viewModel.uiState.value

        assertTrue(state.isLoading)
        assertNull(state.errorMessage)
        assertNull(state.item)

    }

    //Test 2: Successful data retrieval
    @Test
    fun `success emits exact luxury item`() = runTest {
        repository = FakeLuxuryItemDetailRepository()
        viewModel = LuxuryItemDetailViewModel(repository)


        viewModel.loadItem("1")
        testDispatcher.scheduler.advanceUntilIdle()
        val expectedItem = LuxuryItemDetailUiModel(
            id = "1",
            title = "Diamond Encrusted Watch",
            category = "Watches",
            subtitle = "Limited Edition",
            description = "An exquisite timepiece adorned with flawless diamonds, combining precision engineering with unparalleled luxury.",
            imageUrl = ""
        )

        val actualItem = viewModel.uiState.value.item

        assertEquals(expectedItem, actualItem)
        assertFalse(viewModel.uiState.value.isLoading)
        assertNull(viewModel.uiState.value.errorMessage)

    }

    // Test 3: Error state mapping
    @Test
    fun `error emits error state`() = runTest {
        repository2 = FailingLuxuryItemDetailRepository()
        viewModel = LuxuryItemDetailViewModel( repository2)

        viewModel.loadItem("2")

        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.uiState.value

        assertFalse(state.isLoading)
        assertNull(state.item)
        assertEquals("Item not found", state.errorMessage)
    }


}