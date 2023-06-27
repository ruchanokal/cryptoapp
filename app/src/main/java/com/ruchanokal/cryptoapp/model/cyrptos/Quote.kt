package com.ruchanokal.cryptoapp.model.cyrptos


import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("USD")
    val uSD: USD
)