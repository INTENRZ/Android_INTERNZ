package com.example.internz.data.profile


data class ProfileTimelineData (
    /* 타임라인 리스트 Data */
    val timelineIdx: Int,
    val userIdx: String,
    val title: String,
    val start_date: String,
    val end_date: String,
    val category: String
)