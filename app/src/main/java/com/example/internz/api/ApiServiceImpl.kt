package com.example.internz.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceImpl {
    private const val BASE_URL = "http://34.97.205.14:3000"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ApiService::class.java)
}