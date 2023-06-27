package com.ruchanokal.cryptoapp.util

import java.util.*

class Utils {
    companion object {
        @JvmStatic
        fun cryptoRateFormat(rate: Double): String {
            val formattedRate = String.format(Locale.getDefault(), "$%.2f", rate.toDouble())
            return formattedRate
        }
    }
}