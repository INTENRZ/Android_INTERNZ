package com.example.internz.data.signin

import com.google.gson.annotations.SerializedName
import retrofit2.http.Query

//서버 -> 클라이언트
data class SignInData (
    @SerializedName("message")
    val message : String,
    @SerializedName("token")
    val token : String,
    @SerializedName("isnotFirst")
    val isFirst : String //첫 로그인 : 0, 아닌 경우 : 1
)