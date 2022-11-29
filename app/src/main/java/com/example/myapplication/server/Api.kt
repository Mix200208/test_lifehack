package com.example.myapplication.server

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api {

    companion object{
        private val interceptor = run {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }

        val okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .build()

        val api: ServerService = Retrofit.Builder().baseUrl("https://lifehack.studio/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ServerService::class.java)
    }
}