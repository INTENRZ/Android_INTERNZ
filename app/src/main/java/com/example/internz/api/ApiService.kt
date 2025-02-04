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
import com.example.internz.data.follow.FollowerResponseData
import com.example.internz.data.follow.FollowingResponseData
import com.example.internz.data.home.HomeResponseData
import com.example.internz.data.message.MessageListRequestData
import com.example.internz.data.message.MessageListResponseData
import com.example.internz.data.message.MessageResponseData
import com.example.internz.data.message.MessageSendRequestData
import com.example.internz.data.profile.ProfileData
import com.example.internz.data.profile.TimelineStoryListInternData
import com.example.internz.data.signin.SignInData
import com.example.internz.data.signin.SignInRequestData
import com.example.internz.data.signup.SignUpRequestData
import com.example.internz.data.signup2.SignUp2RequestData
import com.example.internz.data.story.*
import com.example.internz.data.timeline.NicknameResponseData
import com.example.internz.feature.calendar.CalenderResponseDayData
import okhttp3.MultipartBody
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
//    @Multipart
    @PUT("/user/taskandintro")
    fun requestFirstSignIn(@Header("token") token : String, @Body body : FirstSignInRequestData ) : Call<CallWithoutDataExt>

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
    fun requestCalenderMonth(@Path("month") month : String, @Header("token") token : String) : Call<BaseResponse<List<CalenderResponseData>>>

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

    // 나의 팔로잉 리스트 조회
    @GET("/profile/following")
    fun requestFollwing(@Header("token") token: String) : Call<BaseResponse<List<FollowingResponseData>>>

    // 나의 팔로 리스트 조회
    @GET("/profile/follower")
    fun requestFollwer(@Header("token") token: String) : Call<BaseResponse<List<FollowerResponseData>>>
    //날짜별 공고 조회(달력)
    @GET("/calender/{day}")
    fun requestCalenderDay(@Path("day") day : String, @Header("token") token : String) : Call<BaseResponse<List<CalenderResponseData>>>

    //캘린더에 공고 추가
    @POST("/calender/{jobIdx}")
    fun requestAddNotification(@Path("jobIdx") jobIdx: String, @Header("token") token: String) : Call<CallWithoutDataExt>

    // 각 타임라인의 스토리 리스트 조회
    @GET("/timeline/{timelineIdx}/story")
    fun requestStoryList(@Header("token") token: String, @Path("timelineIdx") timelineIdx : String) : Call<BaseResponse<List<TimelineStoryListInternData>>>

    //닉네임
    @GET("/user/nickname")
    fun requestNickname(@Header("token") token : String) : Call<BaseResponse<NicknameResponseData>>

    //쪽지 첫화면 데이터 가져오기(쪽지 목록 조회)
    @GET("/letter/others/list")
    fun requestMessage(@Header("token") token : String) : Call<BaseResponse<List<MessageResponseData>>>

    //쪽지 두번째 화면 데이터 가져오기(쪽지 내용 조회)
    @POST("/letter/others/message")
    fun requestMessageList(@Header("token") token : String, @Body body : MessageListRequestData) : Call<BaseResponse<List<MessageListResponseData>>>

    //쪽지 전송
    @POST("/letter/others")
    fun requestSendMessage(@Header("token") token : String,
                           @Body body : MessageSendRequestData) : Call<CallWithoutDataExt>

    //공고 직군 필터링
    @GET("/job/{task}/{sort}")
    fun requestNotification(@Path("task") task : String,
                            @Path("sort") sort : String) : Call<BaseResponse<List<NotificationResponseData>>>

    //타임라인
    @POST("/timeline/{timelineIdx}/story")
    fun requestStoryAdd(@Header("token") token: String, @Path("timelineIdx") timelineIdx : Int, @Body body: StoryAddRequestData) : Call<BaseResponse<StoryAddReponseData>>

}