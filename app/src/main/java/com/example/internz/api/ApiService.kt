package com.example.internz.api

import com.example.internz.common.BaseResponse
import com.example.internz.common.CallWithoutDataExt
import com.example.internz.data.firstsignin.FirstSignInRequestData
import com.example.internz.data.jobselect.JobSelectData
import com.example.internz.data.jobselect.JobSelectPutData
import com.example.internz.data.notification.NotificationResponseData
import com.example.internz.data.profile.ProfileTimelineData
import com.example.internz.data.profile.TimelineRequestData
import com.example.internz.data.signin.SignInData
import com.example.internz.data.signin.SignInRequestData
import com.example.internz.data.signup.SignUpData
import com.example.internz.data.signup.SignUpRequestData
import com.example.internz.data.signup2.SignUp2Data
import com.example.internz.data.signup2.SignUp2RequestData
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
    @POST("/user/signup2") //TODO! 오류 확인
    fun requestSignUp2(@Body body : SignUp2RequestData) : Call<CallWithoutDataExt>

    //관심직군 + 프로필 + 한줄소개
    @PUT("/user/taskandintro")
    fun requestFirstSignIn(@Header("token") token : String, @Body body : FirstSignInRequestData) : Call<CallWithoutDataExt>

    //공고 최신 조회
    @GET("/job")
    fun requestAllNotification() : Call<BaseResponse<List<NotificationResponseData>>>

    @PUT("/user/task")
    fun putJobSelect(@Body body : JobSelectPutData) : Call<JobSelectData>

    //프로필 타임라인 리스트 조회
    @POST("/timeline/list")
    fun responseProfileTimelineList (@Header("token") token: String, @Body body: TimelineRequestData) : Call<BaseResponse<List<ProfileTimelineData>>>
}