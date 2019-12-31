package com.example.internz.data.story

import com.google.gson.annotations.SerializedName

//TODO! userIndex 받지 않기로 함
data class DetailStoryResponseData(
    @SerializedName("title")
    val title: String,
    @SerializedName("content")
    val contents: String,
    @SerializedName("created_date")
    val date: String,
    @SerializedName("nickname")
    val nickname: String,
    @SerializedName("front_image")
    val profile: String,
    @SerializedName("introduce")
    val introduce : String,
    @SerializedName("comment_count")
    val commentCount : Int,
    @SerializedName("userIdx")
    val userIndex : Int
)