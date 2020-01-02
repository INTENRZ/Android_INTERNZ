package com.example.internz.data.follow

import com.google.gson.annotations.SerializedName


data class FollowingResponseData (

    @SerializedName("front_image")
    val profileImg : String,

    @SerializedName("nickname")
    val name : String,

    @SerializedName("introduce")
    val desc : String
)