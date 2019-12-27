package com.example.internz.data.story

class StoryDataTemporal {
    fun getStory() : List<StoryData> {
        return listOf(
            StoryData(
                title = "영화번역가는 AI 때문에 사라질 직업인가?",
                nickname = "한한한",
                date = "20.12.13"
            ),
            StoryData(
                title = "코딩 테스트부터 코딩 인턴까지 코딩에 대한 A to Z",
                nickname = "하은하은",
                date = "20.12.13"
            ),
            StoryData(
                title = "비전공자가 알아본 외국계 디자인 인턴과정",
                nickname = "소미닝",
                date = "20.12.15"
            )
        )
    }
}