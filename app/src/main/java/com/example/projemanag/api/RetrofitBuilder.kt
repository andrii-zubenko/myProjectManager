package com.example.projemanag.api

import com.example.projemanag.utils.Constants.AUTH_BASE_URL
import com.example.projemanag.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAuthBuilder {
    private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY)
    private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logging)
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(AUTH_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}

object RetrofitBuilder {
    private val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY)
    private val httpClient: OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logging)
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}