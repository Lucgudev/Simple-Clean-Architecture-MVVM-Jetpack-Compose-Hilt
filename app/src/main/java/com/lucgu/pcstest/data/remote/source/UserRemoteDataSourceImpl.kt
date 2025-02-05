package com.lucgu.pcstest.data.remote.source

import com.lucgu.pcstest.data.remote.model.DataState
import com.lucgu.pcstest.data.remote.services.ApiServices
import com.lucgu.pcstest.domain.entities.UserEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(private val services: ApiServices) : BaseRemoteDataSource(), UserRemoteDataSource {
    override suspend fun getListUser(): Flow<DataState<List<UserEntity>>> = getResult {
        services.getListUser()
    }
}