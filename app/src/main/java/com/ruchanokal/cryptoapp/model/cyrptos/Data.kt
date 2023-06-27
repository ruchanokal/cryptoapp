package com.ruchanokal.cryptoapp.model.cyrptos


import com.google.gson.annotations.SerializedName

data class Data(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("quote")
    val quote: Quote,
    @SerializedName("symbol")
    val symbol: String

)