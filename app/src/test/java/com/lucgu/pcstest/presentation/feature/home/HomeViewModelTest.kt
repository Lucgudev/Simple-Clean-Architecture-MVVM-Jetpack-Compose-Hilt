package com.lucgu.pcstest.presentation.feature.home

import app.cash.turbine.test
import com.lucgu.pcstest.data.model.APIError
import com.lucgu.pcstest.data.remote.model.DataState
import com.lucgu.pcstest.domain.entities.UserEntity
import com.lucgu.pcstest.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private val userRepository: UserRepository = mock()

    private lateinit var viewModel: HomeViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Use test dispatcher for coroutines
        viewModel = HomeViewModel(userRepository)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset dispatcher after tests
    }

    @Test
    fun `fetchListUser success should emit loading and success states`() = runTest {
        val fakeUserList = listOf(
            UserEntity("49", "2024-08-26T03:04:46.050Z", "Terry O'Reilly", "https://loremflickr.com/640/480/people", "New Avis", "Nauru", "Berkshire", "854", "Davis Mill", "Davis Mill")
        )


        `when`(userRepository.getListUser()).thenReturn(flow {
            emit(DataState.Loading())
            emit(DataState.Success(fakeUserList))
        })

        viewModel.uiState.test {
            awaitItem()
            viewModel.fetchListUser()
            val loadingState = awaitItem()
            assertTrue(loadingState.isLoading)

            val successState = awaitItem()
            assertFalse(successState.isLoading)
            assertEquals(fakeUserList, successState.listUser)
            cancelAndIgnoreRemainingEvents()
        }
    }


    @Test
    fun `fetchListUser error should emit loading and error states`() = runTest {

        `when`(userRepository.getListUser()).thenReturn(flow {
            emit(DataState.Loading())
            emit(DataState.Error(APIError(500, "Error Message")))
        })


        viewModel.uiState.test {
            awaitItem()
            viewModel.fetchListUser()
            val loadingState = awaitItem()
            assertTrue(loadingState.isLoading)

            val errorState = awaitItem()
            assertFalse(errorState.isLoading)
            assertEquals("Error Message", errorState.errorMessage)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `navigateToDetail trigger event`() = runTest {
        val fakeUser = UserEntity("49", "2024-08-26T03:04:46.050Z", "Terry O'Reilly", "https://loremflickr.com/640/480/people", "New Avis", "Nauru", "Berkshire", "854", "Davis Mill", "Davis Mill")
        val fakeUserList = listOf(fakeUser)

        `when`(userRepository.getListUser()).thenReturn(flow {
            emit(DataState.Loading())
            emit(DataState.Success(fakeUserList))
        })

        viewModel.navigateToDetail(fakeUser)

        viewModel.uiEvent.test {
            val event = awaitItem()
            assertTrue(event is HomeViewEvent.NavigateToDetail)
            cancelAndIgnoreRemainingEvents()
        }
    }
}