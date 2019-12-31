package com.example.internz.data.filter

import com.google.gson.annotations.SerializedName

data class FilterResponseData(
    @SerializedName("company")
    val company : String,
    @SerializedName("d_day")
    val day : Int,
    @SerializedName("url")
    val url : String,
    @SerializedName("team")
    val team : String,
    @SerializedName("logo")
    val logo : String
)