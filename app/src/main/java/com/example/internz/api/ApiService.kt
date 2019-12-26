package com.example.internz.api

import com.example.internz.data.signin.SignInData
import com.example.internz.data.signin.SignInRequestData
import com.example.internz.data.SignUpData
import com.example.internz.data.SignUpRequestData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/user/signin")
    fun requestSignIn(@Body body : SignInRequestData) : Call<SignInData>

    @POST("/user/signup")
    fun requestSignUp(@Body body : SignUpRequestData) : Call<SignUpData>
}