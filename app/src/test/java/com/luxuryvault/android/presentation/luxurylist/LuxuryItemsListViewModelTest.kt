package com.luxuryvault.android.presentation.luxurylist

import com.luxuryvault.android.data.fake.FailingLuxuryItemRepositoryTest
import com.luxuryvault.android.data.fake.FakeLuxuryItemRepositoryTest
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * StandardTestDispatcher is a coroutine dispatcher designed for deterministic
 * testing of coroutine-based code. Unlike real dispatchers, it does not execute
 * coroutines immediately. Instead, it queues coroutine work and allows the test
 * to control exactly when that work is executed.
 *
 * This explicit control makes coroutine tests predictable and repeatable,
 * eliminating flakiness caused by timing, thread scheduling, or delays.
 *
 * advanceUntilIdle() advances the virtual test scheduler until there is no
 * remaining pending coroutine work. This includes launched coroutines, Flow
 * emissions, and suspending operations.
 *
 * In practice, calling advanceUntilIdle() ensures that all asynchronous work
 * triggered by the ViewModel (such as collecting a Flow or updating StateFlow)
 * has completed before assertions are made.
 *
 * Without advanceUntilIdle(), tests may observe intermediate states (such as
 * loading) instead of the final expected state, leading to false failures.
 */

@OptIn(ExperimentalCoroutinesApi::class)
class LuxuryItemsListViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: LuxuryItemsListViewModel

    //truth source for the test.
    private fun expectedLuxuryItems(): List<LuxuryItemUiModel> =
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
        )

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        val fakeRepository = FakeLuxuryItemRepositoryTest()
        viewModel = LuxuryItemsListViewModel(fakeRepository)


    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /**
     * This test verifies that the ViewModel emits a loading state followed by a success state containing
     * a list of luxury items when the repository successfully provides data.
     *
     * The purpose of this test is to ensure that :
     * - ViewModel correctly interprets Resource.Success
     * - loading flags are cleared after data arrives
     * - final UI state contains the expected items
     *
     * No Android framework components, databases, or network calls are involved, making this a pure unit test.
     * The ViewModel is exercised as a pure state machine driven by controlled input flows
     * */

    @Test
    fun `when repository emits success, ui state contains items`() = runTest {

        testDispatcher.scheduler.advanceUntilIdle()

        val uiState = viewModel.state.value

        TestCase.assertFalse(uiState.isLoading)
        TestCase.assertEquals(3, uiState.items.size)
        TestCase.assertNull(uiState.errorMessage)
        TestCase.assertEquals("Diamond Encrusted Watch", uiState.items[0].title)

    }

    //Compare entire lists
    /**
     * This test ensures:
     *
     * No accidental shared mutable state
     * ViewModel owns its own state.

    Test: Success Emits Exact Object Graph
    This is the data-integrity test.

     We are not testing the fake.
     We are testing that:

     “The ViewModel does not corrupt or reinterpret domain data.”
     The fake is just a controlled input.

    Mental model:
    Size tests check quantity
    Equality tests check integrity
     * */
    @Test
    fun `uiState contains exact items emitted by repository`() = runTest {
        testDispatcher.scheduler.advanceUntilIdle()

        val actualItems = viewModel.state.value.items
        val expectedItems = expectedLuxuryItems()

        assert(actualItems !== expectedItems) //different references
        TestCase.assertEquals(expectedItems, actualItems) // same content
    }

    /**
     * This test verifies that the ViewModel exposes a loading state
     * when the repository emits Resource.Loading.
     *
     * The intent is to validate that the screen can reactively
     * display loading indicators without coupling itself to
     * asynchronous control flow or repository internals.
     */
    @Test
    fun `when repository emits loading, ui state reflects loading`() = runTest {

        val repository = FakeLuxuryItemRepositoryTest()

        val viewModel = LuxuryItemsListViewModel(repository)


        val state = viewModel.state.value

        TestCase.assertTrue(state.isLoading)
        TestCase.assertTrue(state.items.isEmpty())
        TestCase.assertNull(state.errorMessage)
    }

    /**
     * This test verifies that the ViewModel exposes an error message
     * and clears the loading flag when the repository emits an error.
     *
     * This ensures that failure handling remains localized within
     * the ViewModel and that the UI receives a stable, renderable
     * state even when data retrieval fails.
     */
    @Test
    fun `when repository emits error, ui state contains error message`() = runTest {

        val repository = FailingLuxuryItemRepositoryTest()
        val viewModel = LuxuryItemsListViewModel(repository)

        testDispatcher.scheduler.advanceUntilIdle()

        val state = viewModel.state.value

        TestCase.assertFalse(state.isLoading)
        TestCase.assertTrue(state.items.isEmpty())
        TestCase.assertEquals("Database failure", state.errorMessage)
    }

}

/**
 What are we actually achieving with this testing?

 We are NOT testing:

 Room
 Retrofit
 Flow internals
 Resource class
 Compose UI

 We ARE testing:

 State ownership and transformation logic inside the ViewModel.

 Specifically:

 The ViewModel subscribes to the repository
 It collects a Flow
 It interprets Resource.Success
 It maps data into LuxuryItemListUiState
 It exposes stable, observable state

 This guarantees that:

 UI screens stay dumb
 UI logic never leaks into composables
 Future data source changes won’t break UI behavior

 This is exactly the idea:

 UI renders state, not objects.
 * */