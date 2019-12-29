package com.example.internz.data.signup2

import com.google.gson.annotations.SerializedName

data class SignUp2Data (
    @SerializedName("message")
    val message : String?,
    @SerializedName("success")
    val success : Boolean
)