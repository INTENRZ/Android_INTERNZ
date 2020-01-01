package com.example.internz.feature.calendar

object CalendarHelper {
    //변수 초기화
    var color = listOf<String>(
        "#FF0000",
        "#0054FF",
        "#FF007F",
        "#00D8FF",
        "#191919",
        "#2F9D27",
        "#8041D9",
        "#FF5E00",
        "#ABF200",
        "#747474",
        "#993800"
    )
    var count = 0

    fun getColor() : String {
        if (count > 10) {
            count = 0
        }

        return color[count]
    }
}