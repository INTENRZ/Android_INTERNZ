package com.example.internz.data.comment

import com.google.gson.annotations.SerializedName

data class CommentResponseData(
    @SerializedName("nickname")
    val nickname : String,
    @SerializedName("front_image")
    val profile : String,
    @SerializedName("content")
    val content : String,
    @SerializedName("created_date")
    val date : String
)