package com.example.internz.data.story

import com.google.gson.annotations.SerializedName

data class StoryCategoryResponseData(
    @SerializedName("title")
    val title: String,
    @SerializedName("nickname")
    val nickname : String,
    @SerializedName("created_date")
    val date : String
)