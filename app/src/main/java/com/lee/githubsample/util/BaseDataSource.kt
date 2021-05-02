package com.lee.githubsample.util

import retrofit2.Response

/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseDataSource {

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): RemoteResult<T> {

        try {
            val response = call()

            if (response.isSuccessful) {
                //when(response.code)
                val body = response.body()
                if (body != null) return RemoteResult.success(body)
            }

            return error(" ${response.code()} ${response.message()}")

        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): RemoteResult<T> {
        return RemoteResult.error(message)
    }
}