package com.lucgu.pcstest.presentation.feature.home

import androidx.lifecycle.viewModelScope
import com.lucgu.pcstest.data.remote.model.DataState
import com.lucgu.pcstest.domain.entities.UserEntity
import com.lucgu.pcstest.domain.repository.UserRepository
import com.lucgu.pcstest.domain.viewstate.IViewEvent
import com.lucgu.pcstest.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel<HomeViewState, HomeViewEvent>() {

    override fun createInitialState(): HomeViewState = HomeViewState()
    override fun onTriggerEvent(event: HomeViewEvent) {
        TODO("Not yet implemented")
    }

    init {
        fetchListUser()
    }

    fun fetchListUser() {
        viewModelScope.launch {
            userRepository.getListUser().collect {
                when (it) {
                    is DataState.Error -> {
                        setState {
                            currentState.copy(
                                isLoading = false,
                                errorMessage = it.apiError?.message ?: "General Error Message"
                            )
                        }
                    }

                    is DataState.Loading -> {
                        setState { currentState.copy(isLoading = true) }
                    }

                    is DataState.Success -> {
                        setState {
                            currentState.copy(
                                isLoading = false,
                                listUser = it.data,
                                errorMessage = "",
                            )
                        }
                    }
                }
            }
        }
    }

    fun navigateToDetail(user: UserEntity) {
        setEvent(HomeViewEvent.NavigateToDetail(user))
    }
}

sealed class HomeViewEvent : IViewEvent {
    data class NavigateToDetail(
        val user: UserEntity,
    ) : HomeViewEvent()
}