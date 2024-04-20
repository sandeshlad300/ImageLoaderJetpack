package com.sandesh.acharya

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://acharyaprashant.org/api/v2/"

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService: ApiService = retrofit.create(ApiService::class.java)