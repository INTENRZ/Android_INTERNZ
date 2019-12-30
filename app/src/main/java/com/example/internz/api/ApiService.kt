package com.example.internz.api

import com.example.internz.common.BaseResponse
import com.example.internz.data.jobselect.JobSelectData
import com.example.internz.data.jobselect.JobSelectPutData
import com.example.internz.data.signin.SignInData
import com.example.internz.data.signin.SignInRequestData
import com.example.internz.data.signup.SignUpData
import com.example.internz.data.signup.SignUpRequestData
import com.example.internz.data.signup2.SignUp2Data
import com.example.internz.data.signup2.SignUp2RequestData
import com.example.internz.data.story.StoryData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //로그인
    @POST("/user/signin")
    fun requestSignIn(@Body body: SignInRequestData) : Call<BaseResponse<SignInData>>

    //회원가입 첫번째 단계
    @POST("/user/signup1")
    fun requestSignUp(@Body body : SignUpRequestData) : Call<BaseResponse<SignUpData>>

    //회원가입 두번째 단계
    @PUT("/user/signup2/{userIdx}") //TODO! 오류 확인
    fun requestSignUp2(@Path("userIdx") userIdx : String, @Body body : SignUp2RequestData) : Call<BaseResponse<SignUp2Data>>


//    @POST("/story/category")
//    fun requestStory() : Call<List<StoryData>>
//   fun requestStory(@Body body : String) : Call<List<StoryData>>
//
    @PUT("/user/task")
    fun putJobSelect(@Body body : JobSelectPutData) : Call<JobSelectData>
}