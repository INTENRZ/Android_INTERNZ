package com.example.internz.feature.calendar

import android.util.Log

object CalendarHelper {
    //변수 초기화
    var monthDay = setOf<String>()

    fun setMonthDay(day: String?) {
        monthDay = monthDay.plus(day?.substring(5)!!)
    }

    fun getMonthDayCustom(): Set<String> {
        return monthDay
    }
}