package com.example.internz.feature.calendar

import android.util.Log

object CalendarHelper {
    //변수 초기화
    var monthDay = listOf<String>()

    fun setMonthDay(day: String) {
        monthDay += monthDay.plus(day.substring(5))
        Log.e("TAG", "CalendarHelper, monthDay : ${monthDay}")
    }

    fun getMonthDayCustom(): List<String> {
        return monthDay
    }
}