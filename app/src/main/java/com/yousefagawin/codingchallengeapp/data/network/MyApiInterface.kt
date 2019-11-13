package com.yousefagawin.codingchallengeapp.data.network

import com.yousefagawin.codingchallengeapp.datamodels.ITunesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface MyApiInterface {

    //suspend is used to make suspending functions, suspending functions are the center of everything in coroutines
    //a suspending function is a function that can be paused and resumed at a later time, this can execute long running operations like network operations
    //for the meantime we put everything in @GET
    @GET("search?term=star&amp;country=au&amp;media=movie&amp;all")
    suspend fun getFromITunes(): Response<ITunesResponse>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApiInterface{

            val ITUNES_BASE_URL = "https://itunes.apple.com/"

//            search?term=star&amp;country=au&amp;media=movie&amp;all

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            //this will handle the timeout time of the callback

            val okHttpClient = OkHttpClient.Builder()
                    //just in case for interceptor
//                .addInterceptor(logging)
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                //just in case for encoder
//                .addInterceptor(MyInterceptor())
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(ITUNES_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(MyApiInterface::class.java)
        }
    }
}