package com.lucgu.pcstest.di

import com.lucgu.pcstest.data.remote.services.ApiServices
import com.lucgu.pcstest.data.remote.source.UserRemoteDataSource
import com.lucgu.pcstest.data.remote.source.UserRemoteDataSourceImpl
import com.lucgu.pcstest.data.repository.UserRepositoryImpl
import com.lucgu.pcstest.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiServices =
        retrofit.create(ApiServices::class.java)

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(apiService: ApiServices): UserRemoteDataSource =
        UserRemoteDataSourceImpl(apiService)

    @Provides
    @Singleton
    fun provideUserRepository(userRemoteDataSource: UserRemoteDataSource): UserRepository =
        UserRepositoryImpl(userRemoteDataSource)

}