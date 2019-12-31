package com.example.internz.data.home

data class HomeResponseData (
    val task : List<RecommNoticeData>,
    val profile : List<RecommProfileData>,
    val story : List<TodayStoryData>
)
