
<!-- 
![intro](https://user-images.githubusercontent.com/31889335/71723127-d0a55d80-2e6e-11ea-8ad8-1e3d0c12b3e8.PNG) -->

<center><img src = "https://user-images.githubusercontent.com/31889335/71722639-e4e85b00-2e6c-11ea-9022-6031cba8f8c5.png" width = "300" ></center>

<br>

# 인턴 경험의 모든 것, 인턴즈!
인턴 경험이 중요시 되는 요즘! 

많은 대학생들은 여기 저기에서 인턴 공고를 찾아다니고 정보를 얻기 위해 발버둥을 치고 있습니다. 

저희 인턴즈는 인턴을 준비하는 과정 속에서 발생하는 대학생들의 고민을 해결하기 위해 __맞춤 인턴 정보 추천__ , __캘린더를 통한 인턴 공고 관리__ , __프로필 타임라인과 스토리를 통한 경험 공유 및 소통__ 을 위한 서비스를 제공하고 있습니다. 

인턴 경험의 시작부터 마무리까지! 인턴즈와 함께하세요~

<br>

# 개발 기간

2019.12.23 - 2020.01.04

<br>

# Workflow

![wo](https://user-images.githubusercontent.com/31889335/71723070-950a9380-2e6e-11ea-8a10-33a77803d9cc.png)

<br>

# 1. 프로젝트 사용 라이브러리

-  view pager , tablayout

    ~~~kotlin
    implementation 'com.android.support:design:29.1.0'
    ~~~

    홈 화면 중앙에 있는 맞춤 공고와 onBoarding을 구현하기 위해 사용

    <br>

- glide

    ~~~kotlin
    implementation 'com.github.bumptech.glide:glide:4.10.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.10.0'
    ~~~

    프로필 원형 이미지 및 공고 이미지를 보여주기 위해 사용

    <br>

- retrofit, Gson

    ~~~kotlin
    implementation 'com.squareup.retrofit2:retrofit:2.6.2'
    implementation 'com.squareup.retrofit2:retrofit-mock:2.6.2'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.2'
    implementation 'com.google.code.gson:gson:2.8.6'
    ~~~

    서버 통신을 위해 사용

    <br>

- recyclerview

    ~~~kotlin
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
    ~~~

    반복되는 아이템들을 효과적으로 구현하기 위해 사용

    <br>

- calendar

    ~~~kotlin
    implementation 'com.prolificinteractive:material-calendarview:1.4.3'
    ~~~

    커스텀 달력을 구현하기 위해 사용

    <br>

- circle Indicator 

    ~~~kotlin
    implementation 'me.relex:circleindicator:1.2.2'
    ~~~

    viewPager의 indicator 변경을 위해 사용

    <br>

- swipe menu Layout

    ~~~kotlin
    implementation 'com.github.anzaizai:EasySwipeMenuLayout:1.1.4'
    ~~~

    스와이프 하여 공고 추가를 위해 사용

    <br>

- swipe for refresh

    ~~~kotlin
    implementation 'androidx.appcompat:appcompat:1.0.0'
    ~~~

    당겨서 새로고침 하기위해 사용

    <br>

- snapHelper

    ~~~kotlin
    implementation 'com.github.rubensousa:gravitysnaphelper:2.2.0'
    ~~~

    보다 더 자연스러운 스와이프 효과를 구현하기 위해 사용

    <br>

- 권한 허용

    ~~~kotlin
    implementation 'gun0912.ted:tedpermission:2.1.0'
    ~~~

    디바이스에서 사진을 가져오기 위해 사용

    <br>

# 2. 프로그램 구조

| Activity | 설명 | 
|---|:---:|
| `SplashAcitivity` | 앱 실행시 첫 화면 | 
| `SigninAcitivity` | <P> 로그인 성공 시 MainActivity로 이동 <P> 첫 로그인 시 JobSelectAcitivity로 이동 <p> 회원가입 시 SignUpAcitivity로 이동|  
| `SignupAcitivity` | 이메일, 비밀번호, 핸드폰 번호, 닉네임, 성별, 약관동의 |  
| `JobSelectAcitivity` | 무 선택 후 프로필 사진과 한줄 소개 작성 | 
| `BottomBarActivity` | 홈, 공고, 스토리, 프로필 하단 탭 | 
| `HomeFragment` | 맞춤 공고, 추천 프로필, 오늘의 스토리 | 
| `NotificationFragment` | 인턴 공고 리스트(최신순, 조회순) |  
| `StoryActivity` | 스토리 리스트(최신순, 조회순) | 
| `ProfileActivity` | <p> 개인 프로필 정보 <p> 개인 프로필 정보 <p> 타임라인 리스트 | 
| `CommentAcitivity` | 댓글을 주고 받을 수 있는 화면 | 
| `followerActivity` | 팔로워 리스트 확인할 수 있는 화면 | 
| `followingActivity` | 팔로잉 리스트 확인 | 
| `DetailStoryAcitivity` | 상세 스토리 확인 | 
| `CalendarActivity` | 달력에 공고 일정 추가 및 확인 | 
| `MessageActivity` | 쪽지를 주고 받을 수 있는 화면 | 
| `filterActivity` | 공고 필터를 선택할 수 있는 화면 | 
| `onboardingActivity` | 어플 처음 실행시 어플을 소개하는 화면 | 
| `WebViewActivity` | 공고 클릭시 링크로 이동하는 화면 | 

<br>

# 3. 패키지 구조

| Package | 설명 | 
|---|:---:|
| `api` | 서버 통신을 위한 패키지 | 
| `common` | Extension function을 위한 패키지 | 
| `data` | data 관리를 위한 패키지 | 
| `feature` | 보조 기능을 구분하기 위한 패키지 | 
| `ui` | 주요 기능을 구분하기 위한 패키지 | 

<br>

# 4. 핵심 기능 구현 방법 정리

- __Custom Calendar Implemetation__

    Material 디자인 기반으로 한 달력 라이브러리를 사용하여 캘린더 UI를 구현.

    월별 일자  와 해당 월의 전체 공고 일자를 비교하여 일치하는 날짜에 표시

    ![internz1](https://user-images.githubusercontent.com/31889335/71724794-ef0e5780-2e74-11ea-85d0-1ba3ee134d56.gif)

    <br>

- __Bottom Navigation Bar__

    하단의 탭 아이템과 각 프래그먼트를 연결

    hide(), show() 메서드를 사용하여 각 프래그먼트의 상태 유지

    <br>

- __필터에 따른 공고 확인 기능__

    필터를 적용하여 27가지 이상의 맞춤형 공고 리스트 확인 가능

    <br>

- __팔로잉 팔로우 기능__

    상호간의 팔로잉, 팔로우가 가능하며 팔로우, 팔로잉된 리스트를 볼 수 있음

    <br>

- __스토리 주제 세분화__

    다양한 주제의 스토리 작성 및 확인 가능

    <br>

- __쪽지 기능__

    상호간의 쪽지 보내기 및 받기 가능

    <br>

# 5. Extension function & Lambda

- Body에 data가 없는 경우 사용한 Extension function

~~~kotlin
data class CallWithoutDataExt(
    val message: String,
    val status: Int,
    val success: Boolean
)


fun Call<CallWithoutDataExt>.enqueue(
    onError: (Throwable) -> Unit = onStandardError,
    onSuccess: (CallWithoutDataExt) -> Unit = {},
    onFail: (status: Int, message: String) -> Unit = {_, _ -> Unit}
) {
    this.enqueue(object : Callback<CallWithoutDataExt> {
        override fun onFailure(call: Call<CallWithoutDataExt>, t: Throwable) {
            onError(t)
        }

        override fun onResponse(call: Call<CallWithoutDataExt>, response: Response<CallWithoutDataExt>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    onSuccess(it)
                } ?: onFail(response.body()?.status?:-1, response.body()?.message.orEmpty())
            } else {
                onFail(response.body()?.status?:-1, response.body()?.message.orEmpty())
            }
        }
    })
}

private val onStandardError: (Throwable) -> Unit = {
    Log.e("CallExt", "network error $it")
}
~~~    

<br>

- Body에 data가 있을 경우 사용한 Extension function
~~~kotlin
data class BaseResponse<T>(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: T?
) 

fun <T> Call<BaseResponse<T>>.enqueue(
    onError: (Throwable) -> Unit = onStandardError,
    onSuccess: (T) -> Unit = {},
    onFail: (status: Int, message: String) -> Unit = {_, _ -> Unit}
) {
    this.enqueue(object : Callback<BaseResponse<T>> {
        override fun onFailure(call: Call<BaseResponse<T>>, t: Throwable) {
            onError(t)
        }

        override fun onResponse(call: Call<BaseResponse<T>>, response: Response<BaseResponse<T>>) {
            if (response.isSuccessful) {
                response.body()?.data?.let {
                    onSuccess(it)
                } ?: onFail(response.body()?.status?:-1, response.body()?.message.orEmpty())
            } else {
                onFail(response.body()?.status?:-1, response.body()?.message.orEmpty())
            }
        }
    })
}

private val onStandardError: (Throwable) -> Unit = {
    Log.e("CallExt", "network error $it")
}
~~~

코드의 반복을 줄이기 위해 call 함수를 extenstion 함

서버에서 받는 body에 기본적으로 포함된 status, message, success가 아닌 추가로 data가 있을 경우를 대비하여 data가 추가된 확장 함수를 만듦

<br>

# Contributor

- [김초희](https://github.com/choheeis)
- [윤주연](https://github.com/otu165)
- [오현택](https://github.com/Grit-Taek)

