package com.lucgu.pcstest.domain.repository

import com.lucgu.pcstest.data.remote.model.DataState
import com.lucgu.pcstest.domain.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun getListUser(): Flow<DataState<List<UserEntity>>>
}