package com.example.internz.data.jobselect

import com.google.gson.annotations.SerializedName

class JobSelectData(
    @SerializedName("success")
    val status : Boolean,
    @SerializedName("message")
    val message : String?
)