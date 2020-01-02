package com.example.internz.data.message

import com.google.gson.annotations.SerializedName

data class MessageListResponseData(
    @SerializedName("receiver") //receiver == userIdx 일 경우 보낸 쪽지
    val receiver : Int,
    @SerializedName("sender")
    val sender : Int,
    @SerializedName("created_date")
    val date : String,
    @SerializedName("content")
    val content : String
)