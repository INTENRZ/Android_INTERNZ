package com.example.internz.data.signup

import com.google.gson.annotations.SerializedName

//서버 -> 클라이언트
data class SignUpData(
    @SerializedName("message")
    val message: String?,
    @SerializedName("success")
    val success : Boolean //TODO! 타입 컨펌
)