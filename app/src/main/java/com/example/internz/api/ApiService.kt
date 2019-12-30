package com.example.internz.api

import com.example.internz.common.BaseResponse
import com.example.internz.data.firstsignin.FirstSignInRequestData
import com.example.internz.data.firstsignin.FirstSignInResponseData
import com.example.internz.data.jobselect.JobSelectData
import com.example.internz.data.jobselect.JobSelectPutData
import com.example.internz.data.signin.SignInData
import com.example.internz.data.signin.SignInRequestData
import com.example.internz.data.signup.SignUpData
import com.example.internz.data.signup.SignUpRequestData
import com.example.internz.data.story.StoryData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    //로그인
    @POST("/user/signin")
    fun requestSignIn(@Body body: SignInRequestData) : Call<BaseResponse<SignInData>>

    //회원가입1
    @POST("/user/signup1")
    fun requestSignUp(@Body body : SignUpRequestData) : Call<SignUpData>

    //관심직군 + 프로필 + 한줄소개 - old version
//    @PUT("/user/taskandintro")
//    fun requestSettingInFistSignIn(@Header("token") token : String, @Body body : FirstSignInRequestData) : Call<FirstSignInResponseData>

    //관심직군 + 프로필 + 한줄소개 - new version
    @PUT("/user/taskandintro")
    fun requestSettingAtFirstSignIn(@Header("token") token : String, @Body body : FirstSignInRequestData) : Call<BaseResponse<FirstSignInResponseData>>

    @POST("/story/category")
    fun requestStory() : Call<List<StoryData>>
//    fun requestStory(@Body body : String) : Call<List<StoryData>>

    @PUT("/user/task")
    fun putJobSelect(@Body body : JobSelectPutData) : Call<JobSelectData>
}