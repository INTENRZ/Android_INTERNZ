package com.example.internz.api

import com.example.internz.data.signin.SignInData
import com.example.internz.data.signin.SignInRequestData
import com.example.internz.data.signup.SignUpData
import com.example.internz.data.signup.SignUpRequestData
import com.example.internz.data.signup2.SignUp2Data
import com.example.internz.data.signup2.SignUp2RequestData
import com.example.internz.data.story.StoryData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/user/signin")
    fun requestSignIn(@Body body : SignInRequestData) : Call<SignInData>

    @POST("/user/signup")
    fun requestSignUp(@Body body : SignUpRequestData) : Call<SignUpData>

    @POST("/user/signup")
    fun requestSignUp2(@Body body : SignUp2RequestData) : Call<SignUp2Data>

    //TODO! 스토리 @GET 추가
    fun requestStory() : Call<List<StoryData>>
}