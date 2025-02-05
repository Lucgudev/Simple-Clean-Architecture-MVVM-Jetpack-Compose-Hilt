package com.lucgu.pcstest.presentation.feature.home

import androidx.compose.runtime.Stable
import com.lucgu.pcstest.domain.entities.UserEntity
import com.lucgu.pcstest.domain.viewstate.IViewState

@Stable
data class HomeViewState(
    val isLoading: Boolean = false,
    val listUser: List<UserEntity> = listOf(),
    val errorMessage: String = "",
): IViewState

