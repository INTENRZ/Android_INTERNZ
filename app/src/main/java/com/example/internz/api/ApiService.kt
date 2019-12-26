package com.example.internz.api

import com.example.internz.data.signin.SignInData
import com.example.internz.data.signin.SignInRequestData
import com.example.internz.data.SignUpData
import com.example.internz.data.SignUpRequestData
import com.example.internz.data.story.StoryData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/user/signin")
    fun requestSignIn(@Body body : SignInRequestData) : Call<SignInData>

    @POST("/user/signup")
    fun requestSignUp(@Body body : SignUpRequestData) : Call<SignUpData>

    //TODO! 스토리 @GET 추가
    fun requestStory() : Call<List<StoryData>>
}