package com.example.internz.api

import com.example.internz.common.BaseResponse
import com.example.internz.common.CallWithoutDataExt
import com.example.internz.data.jobselect.JobSelectData
import com.example.internz.data.jobselect.JobSelectPutData
import com.example.internz.data.profile.ProfileTimelineData
import com.example.internz.data.profile.TimelineAddRequestData
import com.example.internz.data.UserIdxRequestData
import com.example.internz.data.profile.ProfileData
import com.example.internz.data.signin.SignInData
import com.example.internz.data.signin.SignInRequestData
import com.example.internz.data.signup.SignUpData
import com.example.internz.data.signup.SignUpRequestData
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


//    @POST("/story/category")
//    fun requestStory() : Call<List<StoryData>>
//   fun requestStory(@Body body : String) : Call<List<StoryData>>
//
    @PUT("/user/task")
    fun putJobSelect(@Body body : JobSelectPutData) : Call<JobSelectData>

    //타인 프로필 타임라인 리스트 조회
    @POST("/timeline/list")
    fun responseProfileTimelineList (@Header("token") token: String, @Body body: UserIdxRequestData) : Call<BaseResponse<List<ProfileTimelineData>>>

    //자신 프로필 타임라인 리스트 조회
    @POST("/timeline/list")
    fun responseMyTimelineList (@Header("token") token: String) : Call<BaseResponse<List<ProfileTimelineData>>>

    //메인 홈에 있는 추천 프로필 조회


    //새로운 타임라인 추가
    @POST("/timeline")
    fun requestTimelineAdd (@Header("token") token: String, @Body body: TimelineAddRequestData) : Call<BaseResponse<TimelineAddRequestData>>

    //자신 프로필 정보 조회
    @POST("/profile")
    fun requestProfile(@Header("token") token: String) : Call<BaseResponse<ProfileData>>

}