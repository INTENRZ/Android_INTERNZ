package com.example.internz.feature.calendar

import com.google.gson.annotations.SerializedName

//날짜별 공고 조회를 위해서 만들었는데, CalenderResponseData가 정상적으로 작동한다면 삭제해야 함
data class CalenderResponseDayData (
    @SerializedName("logo")
    val logo : String,
    @SerializedName("calenderIdx")
    val calenderIdx : Int,
    @SerializedName("company")
    val company : String,
    @SerializedName("team")
    val team : String,
    @SerializedName("d_day")
    val day : Int
)