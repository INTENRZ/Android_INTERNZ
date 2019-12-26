package com.example.internz.data

import com.google.gson.annotations.SerializedName

data class SignUpData (
    //TODO! 에러 확인
    @SerializedName("message")
    val message : String?
)