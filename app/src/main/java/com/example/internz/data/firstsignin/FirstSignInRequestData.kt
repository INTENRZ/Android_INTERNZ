package com.example.internz.data.firstsignin

import com.google.gson.annotations.SerializedName

//클라이언트 -> 서버
data class FirstSignInRequestData (
    @SerializedName("task_one")
    val task1 : String,
    @SerializedName("task_two")
    val task2 : String,
    @SerializedName("task_three")
    val task3 : String,
    @SerializedName("front_image")
    val image : String,
    @SerializedName("introduce")
    val introduce : String
)