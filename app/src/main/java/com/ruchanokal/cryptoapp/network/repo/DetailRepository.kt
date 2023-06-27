package com.ruchanokal.cryptoapp.network.repo

import com.ruchanokal.cryptoapp.network.ApiService
import javax.inject.Inject

class DetailRepository @Inject constructor(private val apiService: ApiService): BaseRepository() {

        suspend fun getDetail(apiKey : String, symbol :String) = safeApiRequest {
            apiService.getDetail(apiKey,symbol)
        }

}