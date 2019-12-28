package com.example.internz.data.story

import com.google.gson.annotations.SerializedName

//서버 -> 클라이언트
data class StoryData(
    @SerializedName("title")
    val title: String?,
    @SerializedName("nickname")
    val nickname: String?,
    @SerializedName("created_date")
    val date: String?,
    @SerializedName("success")
    val success : String
)