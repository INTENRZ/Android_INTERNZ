package com.example.internz.data.profile


data class TimelineStoryListInternData (
    /* 각 타임라인의 스토리 리스트에 대한 데이터 */
    var storyIdx : Int,
    var timelineIdx : Int,
    var userIdx : Int,
    var title : String?,
    var content : String?,
    var count : Int,
    var created_date : String,
    var updated_date : String,
    var isMe : Int
)
