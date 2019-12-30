package com.example.internz.data.firstsignin

import com.google.gson.annotations.SerializedName

//서버 -> 클라이언트
data class FirstSignInResponseData(
    @SerializedName("status")
    val status : String?,
    @SerializedName("success")
    val success : String,
    @SerializedName("message")
    val message : String
)