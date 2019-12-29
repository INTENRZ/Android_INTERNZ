package com.example.internz.data.story

class StoryDataTemporal2 {
    fun getStory() : List<StoryData> {
        return listOf(
            StoryData(
                title = "여기는 탭을 선택한 내용이 나타날 프래그먼트입니다.",
                nickname = "한한한",
                date = "20.12.13",
                success = "true"
            ),
            StoryData(
                title = "코딩 테스트부터 코딩 인턴까지 코딩에 대한 A to Z",
                nickname = "하은하은",
                date = "20.12.13",
                success = "true"
            ),
            StoryData(
                title = "비전공자가 알아본 외국계 디자인 인턴과정",
                nickname = "소미닝",
                date = "20.12.15",
                success = "true"
            )
        )
    }
}