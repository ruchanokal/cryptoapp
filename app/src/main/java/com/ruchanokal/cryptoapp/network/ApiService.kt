package com.ruchanokal.cryptoapp.network

import com.ruchanokal.cryptoapp.model.cyrptos.CryptoResponse
import com.ruchanokal.cryptoapp.model.detail.DetailResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("v1/cryptocurrency/listings/latest")
    suspend fun getCryptoData(@Header("X-CMC_PRO_API_KEY") apikey : String) : CryptoResponse

    @GET("v2/cryptocurrency/info")
    suspend fun getDetail(
        @Header("X-CMC_PRO_API_KEY") apikey : String,
        @Query("symbol") symbol: String): DetailResponse
}