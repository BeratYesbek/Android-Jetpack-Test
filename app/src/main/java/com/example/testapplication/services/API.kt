package com.example.testapplication.services

import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


open class API {
    companion object
    {
        inline fun httpClientInterceptor(): OkHttpClient {
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()
                    val requestBuild = request.newBuilder().build()
                    return chain.proceed(requestBuild)
                }
            })
            return httpClient.build()
        }

        inline fun<reified T> api(baseUrl : String) :T {
            val client = httpClientInterceptor()
            val gson = GsonBuilder()
                .setLenient()
                .create()
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(client)
                .build()
                .create(T::class.java)

        }
    }

}