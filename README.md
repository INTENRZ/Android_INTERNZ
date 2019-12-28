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

# 2. 프로그램 구조

SplashAcitivity
---

- 앱 실행시 첫 화면

<br>

SigninAcitivity
---

- 로그인 성공 시 MainActivity로 이동
- 첫 로그인 시 JobSelectAcitivity로 이동
- 회원가입 시 SignUpAcitivity로 이동

<br>

SignupAcitivity
---

- 이메일, 비밀번호, 핸드폰 번호, 닉네임, 성별, 약관동의

<br>

JobSelectAcitivity
---

- 직무 선택 후 프로필 사진과 한줄 소개 작성

<br>

MainActivity
---
- 홈, 공고, 스토리, 프로필 이동 가능

<br>

HomeFragment
---
- 맞춤 공고, 추천 프로필, 오늘의 스토리

<br>

NotificationFragment
---
- 인턴 공고 리스트(최신순, 조회순)

<br>

StoryActivity
---
- 스토리 리스트(최신순, 조회순)

<br>

ProfileActivity
---
- 개인 프로필 정보
- 타임라인 리스트

<br>

# 3. 핵심 기능 구현 방법 정리

