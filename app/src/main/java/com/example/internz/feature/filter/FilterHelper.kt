package com.example.internz.feature.filter

import android.widget.CheckedTextView
import com.example.internz.data.filter.FilterItem

object FilterHelper {
    var count : Int = 0

    //이전의 버튼
    var lastButton : CheckedTextView? = null
    //현재 버튼
    var currentButton : CheckedTextView? = null
}