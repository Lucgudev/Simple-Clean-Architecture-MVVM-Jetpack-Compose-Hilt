package com.lucgu.pcstest.presentation.feature.detail

import androidx.compose.runtime.Stable
import com.lucgu.pcstest.domain.entities.UserDetailModel
import com.lucgu.pcstest.domain.viewstate.IViewState

@Stable
data class DetailViewState(
    val userDetailModel: UserDetailModel? = null,
): IViewState
