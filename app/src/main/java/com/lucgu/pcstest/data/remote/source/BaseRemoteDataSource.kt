package com.lucgu.pcstest.data.remote.source

import com.google.gson.Gson
import com.lucgu.pcstest.data.model.APIError
import com.lucgu.pcstest.data.remote.model.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response

open class BaseRemoteDataSource {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Flow<DataState<T>> {
        return flow<DataState<T>> {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) emit(DataState.Success(body))
                else {
                    val apiError: APIError =
                        Gson().fromJson(response.errorBody()?.charStream(), APIError::class.java)
                    emit(DataState.Error(apiError))
                }
            } else {
                val apiError: APIError =
                    Gson().fromJson(response.errorBody()?.charStream(), APIError::class.java)
                emit(DataState.Error(apiError))
            }

        }
            .catch {
                emit(DataState.Error(APIError(-1, it.message ?: it.toString())))
            }
            .onStart { emit(DataState.Loading()) }
            .flowOn(Dispatchers.IO)
    }
}