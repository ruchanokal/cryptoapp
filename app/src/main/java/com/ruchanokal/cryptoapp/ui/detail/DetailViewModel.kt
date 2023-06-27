package com.ruchanokal.cryptoapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.ruchanokal.cryptoapp.model.detail.CryptoDetail
import com.ruchanokal.cryptoapp.model.detail.DetailResponse
import com.ruchanokal.cryptoapp.network.repo.DetailRepository
import com.ruchanokal.cryptoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val detailRepository: DetailRepository): ViewModel() {

    private val _detailInfo = MutableLiveData<CryptoDetail?>()
    val detailInfo : LiveData<CryptoDetail?> = _detailInfo
    private val _error = MutableLiveData<String>()
    val error : LiveData<String> = _error
    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading : LiveData<Boolean> = _isLoading

    fun getDetailData(apiKey :String, symbol: String) = viewModelScope.launch {
        val request = detailRepository.getDetail(apiKey,symbol)
        when(request){
            is Resource.Success -> {
                _isLoading.value = false

                val gson = Gson()
                val json = gson.toJson(request.data?.data)
                val jsonObject = JSONObject(json)
                val jsonArray = jsonObject[symbol] as JSONArray

                val cryptoData = gson.fromJson(jsonArray.getJSONObject(0).toString(), CryptoDetail::class.java)
                _detailInfo.value = cryptoData

            }

            is Resource.Error -> {
                _isLoading.value = false
                _error.value = request.message.toString()
            }

            else -> {
                _isLoading.value = false
                _error.value = "There is an unknown error!"
            }

        }
    }




}