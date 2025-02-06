package com.lucgu.pcstest.presentation.feature.detail

import app.cash.turbine.test
import com.lucgu.pcstest.domain.entities.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Use test dispatcher for coroutines
        viewModel = DetailViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset dispatcher after tests
    }

    @Test
    fun `fetchListUser success should emit loading and success states`() = runTest {
        val fakeUser = UserEntity("49", "2024-08-26T03:04:46.050Z", "Terry O'Reilly", "https://loremflickr.com/640/480/people", "New Avis", "Nauru", "Berkshire", "854", "Davis Mill", "12345")



        viewModel.uiState.test {
            awaitItem()
            viewModel.setUserDetail(fakeUser)
            val result = awaitItem()
            assertEquals(result.userDetailModel?.firstName, "Terry")
            assertEquals(result.userDetailModel?.lastName, "O'Reilly")
            assertEquals(result.userDetailModel?.address, "854 Davis Mill 12345")
            cancelAndIgnoreRemainingEvents()
        }
    }
}