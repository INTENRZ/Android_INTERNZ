package com.example.internz.data.profile


data class ProfileTimelineData (
    val timelineIdx: Int,
    val userIdx: Int,
    val title: String,
    val start_date: String,
    val end_date: String,
    val category: String
)