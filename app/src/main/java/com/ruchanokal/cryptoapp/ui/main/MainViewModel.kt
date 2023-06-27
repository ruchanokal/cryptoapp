package com.ruchanokal.cryptoapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruchanokal.cryptoapp.model.cyrptos.CryptoResponse
import com.ruchanokal.cryptoapp.network.repo.MainRepository
import com.ruchanokal.cryptoapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

    private var _cryptoList = MutableLiveData<CryptoResponse?>()
    var cryptoList : LiveData<CryptoResponse?> = _cryptoList

    private var _errorMessage = MutableLiveData<String?>()
    var errorMessage : LiveData<String?> = _errorMessage

    private var _isLoading = MutableLiveData<Boolean>(true)
    var isLoading : LiveData<Boolean> = _isLoading


    fun getCryptoData(apiKey: String) = viewModelScope.launch {
        _isLoading.value = true
        val request = mainRepository.getCryptoData(apiKey)
        when(request){
            is Resource.Success -> {

                Log.e("MainViewModel","data-2: " + request.data?.data)
                Log.e("MainViewModel","data-3: " + request.data)
                _cryptoList.value = request.data
                _isLoading.value = false
            }

            is Resource.Error -> {
                _errorMessage.value = request.message
                _isLoading.value = false
            }

            else ->{
                _isLoading.value = false
            }

        }

    }

}