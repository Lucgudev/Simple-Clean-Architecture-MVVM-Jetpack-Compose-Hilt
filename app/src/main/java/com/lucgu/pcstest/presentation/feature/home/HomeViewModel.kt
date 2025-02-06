package com.lucgu.pcstest.presentation.feature.home

import androidx.lifecycle.viewModelScope
import com.lucgu.pcstest.data.remote.model.DataState
import com.lucgu.pcstest.domain.repository.UserRepository
import com.lucgu.pcstest.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
): BaseViewModel<HomeViewState>() {

    override fun createInitialState(): HomeViewState = HomeViewState()

    fun fetchListUser() {
        viewModelScope.launch {
            userRepository.getListUser().collect {
                when(it) {
                    is DataState.Error -> {
                        setState { currentState.copy(isLoading = false, errorMessage = it.apiError?.message ?: "General Error Message") }
                    }
                    is DataState.Loading -> {
                        setState { currentState.copy(isLoading = true) }
                    }
                    is DataState.Success -> {
                        setState { currentState.copy(
                            isLoading = false,
                            listUser = it.data,
                            errorMessage = "",
                        ) }
                    }
                }
            }
        }
    }
}