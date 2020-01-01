package com.example.internz.data.story

data class StoryCategoryRequestData (
    val category : String,
    val sort : String //0 = 최신순, 1 = 조회순
)