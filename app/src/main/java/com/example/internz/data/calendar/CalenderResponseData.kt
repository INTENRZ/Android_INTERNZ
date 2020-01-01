package com.example.internz.data.calendar

import com.google.gson.annotations.SerializedName

//서버 -> 클라이언트 캘린더 전체 조회
data class CalenderResponseData (
    @SerializedName("logo")
    val logo : String,
    @SerializedName("jobIdx")
    val jobIdx : Int,
    @SerializedName("calenderIdx")
    val calendarIndex : Int,
    @SerializedName("company")
    val company : String,
    @SerializedName("team")
    val team : String,
    @SerializedName("d_day")
    val day : Int, //음수, 양수 넘어옴
    @SerializedName("end_date")
    val end_date : String
)