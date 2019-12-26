package com.example.internz.api

import com.example.internz.data.SignInData
import com.example.internz.data.SignInRequestData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignInService {
    @POST("/user/signin")
    fun requestSignIn(@Body body : SignInRequestData) : Call<SignInData>
}