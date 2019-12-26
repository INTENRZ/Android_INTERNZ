package com.example.internz.data

import com.google.gson.annotations.SerializedName

//클라이언트 -> 서버
data class SignInRequestData(
    //TODO! 오류 확인
//    @SerializedName("id")
    val email : String,
//    @SerializedName("pw")
    val pwd : String
)