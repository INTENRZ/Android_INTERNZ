package com.example.internz.data.signup

import com.google.gson.annotations.SerializedName

//클라이언트 -> 서버
data class SignUpRequestData(
    @SerializedName("email")
    val email : String
)