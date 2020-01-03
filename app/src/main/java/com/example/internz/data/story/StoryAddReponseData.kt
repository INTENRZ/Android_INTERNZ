package com.example.internz.data.story

import com.google.gson.annotations.SerializedName

data class StoryAddReponseData(
    val fieldCount: String,
    val affectedRows: String,
    val insertId: String,
    val serverStatus: String,
    val warningCount: String,
    val message: String,
    val protocol41: String,
    val changedRows: String
)