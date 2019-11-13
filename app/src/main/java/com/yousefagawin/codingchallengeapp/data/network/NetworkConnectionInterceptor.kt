package com.yousefagawin.codingchallengeapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.yousefagawin.codingchallengeapp.util.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkConnectionInterceptor (context: Context): Interceptor {

    private val applicationContext = context

    //inside chain is our network request
    override fun intercept(chain: Interceptor.Chain): Response {

        if(!isInternetAvailable())
            throw NoInternetException("Make sure you have an active network connection")

        return chain.proceed(chain.request())
    }

    //this will check if the network connection is available in our device
    private fun isInternetAvailable(): Boolean{

        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {

            return it != null && it.isConnected

        }
    }
}