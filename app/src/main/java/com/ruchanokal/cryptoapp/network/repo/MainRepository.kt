package com.ruchanokal.cryptoapp.network.repo

import com.ruchanokal.cryptoapp.network.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService): BaseRepository() {

    suspend fun getCryptoData(apiKey : String) = safeApiRequest {
        apiService.getCryptoData(apiKey)
    }

}