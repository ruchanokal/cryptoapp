package com.ruchanokal.cryptoapp.di

import com.google.gson.Gson
import com.ruchanokal.cryptoapp.network.ApiService
import com.ruchanokal.cryptoapp.network.repo.DetailRepository
import com.ruchanokal.cryptoapp.network.repo.MainRepository
import com.ruchanokal.cryptoapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideConverterFactory() : GsonConverterFactory{
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(gsonConverterFactory: GsonConverterFactory) : Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(gsonConverterFactory).build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideMainRepository(apiService: ApiService) : MainRepository{
        return MainRepository(apiService)
    }

    @Singleton
    @Provides
    fun provideDetailRepository(apiService: ApiService) : DetailRepository{
        return DetailRepository(apiService)
    }

}