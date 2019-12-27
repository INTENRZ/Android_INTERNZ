package com.example.internz.data.signin

import com.google.gson.annotations.SerializedName

//클라이언트 -> 서버
data class SignInRequestData(
    @SerializedName("email")
    val email : String,
    @SerializedName("password")
    val password : String

)