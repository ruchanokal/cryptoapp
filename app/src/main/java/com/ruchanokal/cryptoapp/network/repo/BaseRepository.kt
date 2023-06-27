package com.ruchanokal.cryptoapp.network.repo

import com.google.gson.Gson
import com.ruchanokal.cryptoapp.CryptoApp.Companion.getAppContext
import com.ruchanokal.cryptoapp.R
import com.ruchanokal.cryptoapp.model.error.ErrorResponse
import com.ruchanokal.cryptoapp.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> safeApiRequest(
        apiRequest: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiRequest.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        Resource.Error(
                            false, errorBodyParser(throwable.response()?.errorBody()?.string())
                        )
                    }
                    else -> Resource.Error(true, throwable.localizedMessage)
                }
            }
        }
    }

    private fun errorBodyParser(error: String?): String {
        error?.let {
            return try {
                val errorResponse = Gson().fromJson(error, ErrorResponse::class.java)
                val errorMessage = errorResponse.status.errorMessage
                errorMessage ?: getAppContext().resources.getString(R.string.error_message)
            } catch (e: Exception) {
                getAppContext().resources.getString(R.string.error_message)
            }
        }
        return getAppContext().resources.getString(R.string.error_message)
    }

}


