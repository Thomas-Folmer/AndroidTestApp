package com.opengroupe.androidtestapp.utils

import android.content.Context
import android.net.ConnectivityManager


class NetworkUtils {
    companion object {
        fun isNetworkConnected(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }
}