package com.example.internz.data.signin

import com.google.gson.annotations.SerializedName

//클라이언트 -> 서버
data class SignInRequestData(
    //TODO! 오류 확인
    @SerializedName("email")
    val email : String,
    @SerializedName("password")
    val password : String
)