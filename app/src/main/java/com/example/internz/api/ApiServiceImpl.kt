package com.example.internz.api

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceImpl {
    private const val BASE_URL = "http://34.97.246.7:3000"
    private lateinit var token : String
    private lateinit var userIdx : String
    private lateinit var receiverIdx : String

    //TODO! 서버에서 토큰을 보내주지 않는 현상 발생

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(ApiService::class.java)

    fun setToken(token: String) {
        this.token = token
    }

    fun getToken() : String {
        return token
    }

    fun setUserIdx(userIdx : String) {
        this.userIdx = userIdx
    }

    fun getUserIdx() : String {
        return userIdx
    }

    fun setReceiverIdx(idx : String) {
        this.receiverIdx = idx
    }

    fun getReceiverIdx() : String {
        return receiverIdx
    }
}