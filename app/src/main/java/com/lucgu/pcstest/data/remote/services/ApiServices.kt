package com.lucgu.pcstest.data.remote.services

import com.lucgu.pcstest.domain.entities.UserEntity
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {
    @GET("getData/test")
    suspend fun getListUser(): Response<List<UserEntity>>
}