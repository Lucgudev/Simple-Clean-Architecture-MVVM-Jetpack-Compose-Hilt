package com.lucgu.pcstest.data.remote.source

import com.lucgu.pcstest.data.remote.model.DataState
import com.lucgu.pcstest.domain.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRemoteDataSource {
    suspend fun getListUser(): Flow<DataState<List<UserEntity>>>
}