package com.example.internz.data.signup

import com.google.gson.annotations.SerializedName

//클라이언트 -> 서버
data class SignUpRequestData(
    @SerializedName("email")
    val email : String,
    @SerializedName("password")
    val password : String,
    @SerializedName("password2")
    val password2 : String,
    @SerializedName("phone")
    val phone : String
)