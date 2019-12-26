package com.example.internz.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SignInServiceImpl {
    private const val BASE_URL = "http://34.97.205.14:3000"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //로그인(SignIn) service
    val singInService = retrofit.create(SignInService::class.java)
}