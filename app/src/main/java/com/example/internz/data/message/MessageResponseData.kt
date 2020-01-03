package com.example.internz.data.message

import com.google.gson.annotations.SerializedName

data class MessageResponseData (
    @SerializedName("userIdx")
    val uesrIdx : Int,
    @SerializedName("nickname")
    val nickname : String,
    @SerializedName("content")
    val content : String,
    @SerializedName("front_image")
    val image : String
)