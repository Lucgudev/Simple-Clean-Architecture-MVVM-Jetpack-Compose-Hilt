package com.lucgu.pcstest.data.repository

import com.lucgu.pcstest.data.remote.model.DataState
import com.lucgu.pcstest.data.remote.source.UserRemoteDataSource
import com.lucgu.pcstest.domain.entities.UserEntity
import com.lucgu.pcstest.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userRemoteDataSource: UserRemoteDataSource): UserRepository {
    override suspend fun getListUser(currentTime: Long): Flow<DataState<List<UserEntity>>> = flow {
        emitAll(userRemoteDataSource.getListUser())
    }

}