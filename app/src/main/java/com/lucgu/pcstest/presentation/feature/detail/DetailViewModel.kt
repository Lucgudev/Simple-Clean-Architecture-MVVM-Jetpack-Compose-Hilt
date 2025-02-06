package com.lucgu.pcstest.presentation.feature.detail

import com.lucgu.pcstest.domain.entities.UserDetailModel
import com.lucgu.pcstest.domain.entities.UserEntity
import com.lucgu.pcstest.domain.viewstate.IViewEvent
import com.lucgu.pcstest.presentation.base.BaseViewModel

class DetailViewModel() : BaseViewModel<DetailViewState, DetailViewEvent>() {

    override fun createInitialState(): DetailViewState = DetailViewState()

    override fun onTriggerEvent(event: DetailViewEvent) {}

    fun setUserDetail(user: UserEntity) {
        val firstName = user.name.split(" ").firstOrNull()
        val lastName = user.name.split(" ").lastOrNull()
        val address = "${user.addressNo} ${user.street} ${user.city} ${user.county} ${user.country} ${user.zipCode}"
        setState {
            currentState.copy(
                userDetailModel = UserDetailModel(
                    firstName = firstName ?: "",
                    lastName = lastName ?: "",
                    address = address,
                    avatar = user.avatar
                )
            )
        }
    }

}

sealed class DetailViewEvent : IViewEvent{

}