# Contributor

- [김초희](https://github.com/choheeis)
- [윤주연](https://github.com/otu165)
- [오현택](https://github.com/Grit-Taek)

<br>

# 1. 프로젝트 사용 라이브러리

-  view pager , tablayout

    ~~~kotlin
    implementation 'com.android.support:design:29.1.0'
    ~~~

    <br>

- circle image

    ~~~kotlin
    implementation 'de.hdodenhof:circleimageview:3.0.1'
    ~~~

    <br>

- retrofit, Gson

    ~~~kotlin
    implementation 'com.squareup.retrofit2:retrofit:2.6.2'
    implementation 'com.squareup.retrofit2:retrofit-mock:2.6.2'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.2'
    implementation 'com.google.code.gson:gson:2.8.6'
    ~~~

    <br>

- recyclerview

    ~~~kotlin
    implementation 'androidx.recyclerview:recyclerview:1.1.0'
    ~~~

    <br>

- calendar

    ~~~kotlin
    implementation 'com.applandeo:material-calendar-view:1.7.0'
    ~~~

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
| `followerActivity` | 팔로워 리스트 확인 | 
| `followingActivity` | 팔로잉 리스트 확인 | 
| `DetailStoryAcitivity` | 상세 스토리 확인 | 
| `CalendarActivity` | 달력에 공고 일정 추가 및 확인 | 
| `MessageAcitivity` | 쪽지를 주고 받을 수 있는 화면 | 

<br>

![ProjectStructure](https://user-images.githubusercontent.com/31889335/71584399-bf4e0f80-2b55-11ea-820f-0e45af0680c9.PNG)


<br>

# 3. 핵심 기능 구현 방법 정리

- Custom Calendar Implemetation

- Bottom Navigation Bar

    하단의 탭 아이템과 각 프래그먼트를 연결

    hide(), show() 메서드를 사용하여 각 프래그먼트의 상태 듀지
