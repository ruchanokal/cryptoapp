package com.ruchanokal.cryptoapp.model.error


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status")
    val status: Status
)