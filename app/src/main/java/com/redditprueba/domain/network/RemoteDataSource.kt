package com.redditprueba.domain.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataSource {



    fun <API> buildApi(
        api: Class<API>,
        url:String
    ): API{
        return  Retrofit.Builder()
            .baseUrl(url)
            .client(OkHttpClient.Builder()
             .build())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(api)

    }


}