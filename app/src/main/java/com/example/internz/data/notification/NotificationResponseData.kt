package com.example.internz.data.notification

import com.google.gson.annotations.SerializedName

//서버 -> 클라이언트
data class NotificationResponseData (
    @SerializedName("status")
    val status : String, //상태코드
    @SerializedName("company")
    val company : String, //회사이름
    @SerializedName("d_day")
    val day : Int, //D-day
    @SerializedName("url")
    val url : String, //클릭시 띄울 url
    @SerializedName("logo")
    val logo : String, //오른편에 보일 회사 로고
    @SerializedName("team")
    val team : String //회사의 공고 설명
)