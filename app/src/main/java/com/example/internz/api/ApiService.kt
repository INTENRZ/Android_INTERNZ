package com.example.internz.api

import com.example.internz.common.BaseResponse
import com.example.internz.common.CallWithoutDataExt
import com.example.internz.data.calendar.CalenderResponseData
import com.example.internz.data.comment.CommentRequestData
import com.example.internz.data.comment.CommentResponseData
import com.example.internz.data.filter.FilterResponseData
import com.example.internz.data.firstsignin.FirstSignInRequestData
import com.example.internz.data.jobselect.JobSelectData
import com.example.internz.data.jobselect.JobSelectPutData
import com.example.internz.data.notification.NotificationResponseData
import com.example.internz.data.profile.ProfileTimelineData
import com.example.internz.data.profile.TimelineAddRequestData
import com.example.internz.data.UserIdxRequestData
import com.example.internz.data.home.HomeResponseData
import com.example.internz.data.profile.ProfileData
import com.example.internz.data.profile.TimelineStoryListInternData
import com.example.internz.data.signin.SignInData
import com.example.internz.data.signin.SignInRequestData
import com.example.internz.data.signup.SignUpRequestData
import com.example.internz.data.signup2.SignUp2RequestData
import com.example.internz.data.story.DetailStoryResponseData
import com.example.internz.data.story.StoryCategoryRequestData
import com.example.internz.data.story.StoryCategoryResponseData
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    //로그인
    @POST("/user/signin")
    fun requestSignIn(@Body body: SignInRequestData) : Call<BaseResponse<SignInData>>

    //회원가입 첫번째 단계
    @POST("/user/signup1")
    fun requestSignUp(@Body body : SignUpRequestData) : Call<CallWithoutDataExt>

    //회원가입 두번째 단계
    @POST("/user/signup2") //TODO! 오류 확인
    fun requestSignUp2(@Body body : SignUp2RequestData) : Call<CallWithoutDataExt>

    //관심직군 + 프로필 + 한줄소개
    @PUT("/user/taskandintro")
    fun requestFirstSignIn(@Header("token") token : String, @Body body : FirstSignInRequestData) : Call<CallWithoutDataExt>

    //공고 최신 조회
    @GET("/job")
    fun requestAllNotification() : Call<BaseResponse<List<NotificationResponseData>>>

    //공고 직군 필터링
    @GET("/job/{task}")
    fun requestJobFilter(@Path("task") task : String) : Call<BaseResponse<List<NotificationResponseData>>>

    //스토리 카테고리/정렬 조회
    @POST("/story/category/sort")
    fun requestCategoryStory(@Body body: StoryCategoryRequestData) : Call<BaseResponse<List<StoryCategoryResponseData>>>

    //스토리 내용 조회
    @GET("/story/{storyIdx}")
    fun requestDetailStory(@Header("token") token : String, @Path("storyIdx") storyIdx : String) : Call<BaseResponse<List<DetailStoryResponseData>>>

    //스토리 댓글 조회
    @GET("/story/{storyIdx}/comment")
    fun requestComment(@Path("storyIdx") storyIdx: String) : Call<BaseResponse<List<CommentResponseData>>>

    //스토리 댓글 생성
    @POST("/story/{storyIdx}/comment")
    fun requestMakeComment(@Header("token") token : String, @Path("storyIdx") storyIdx : String,
                           @Body content : CommentRequestData) : Call<BaseResponse<List<CommentResponseData>>>

    //캘린더 홈 조회
    @GET("/calender/home/{month}")
    fun requestCalenderMonth(@Header("token") token : String, @Path("month") month : String) : Call<BaseResponse<List<CalenderResponseData>>>

    @PUT("/user/task")
    fun putJobSelect(@Body body : JobSelectPutData) : Call<JobSelectData>

    //자신 프로필 정보 조회
    @POST("/profile")
    fun requestMyProfile(@Header("token") token: String) : Call<BaseResponse<ProfileData>>

    //타인 프로필 정보 조회
    @POST("/profile")
    fun requestOtherProfile(@Header("token") token: String, @Body body: UserIdxRequestData) : Call<BaseResponse<ProfileData>>

    //타인 프로필 타임라인 리스트 조회
    @POST("/timeline/list")
    fun responseProfileTimelineList (@Header("token") token: String, @Body body: UserIdxRequestData) : Call<BaseResponse<List<ProfileTimelineData>>>

    //자신 프로필 타임라인 리스트 조회
    @POST("/timeline/list")
    fun responseMyTimelineList (@Header("token") token: String) : Call<BaseResponse<List<ProfileTimelineData>>>

    //메인 홈에 있는 데이터 조회
    @GET("/home")
    fun responseMainHome(@Header("token") token: String) : Call<BaseResponse<HomeResponseData>>

    //새로운 타임라인 추가
    @POST("/timeline")
    fun requestTimelineAdd (@Header("token") token: String, @Body body: TimelineAddRequestData) : Call<BaseResponse<TimelineAddRequestData>>

    // 각 타임라인의 스토리 리스트 조회
    @GET("/timeline/{timelineIdx}/story")
    fun requestStoryList(@Header("token") token: String, @Path("timelineIdx") timelineIdx : String) : Call<BaseResponse<List<TimelineStoryListInternData>>>

}