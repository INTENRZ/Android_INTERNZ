package com.example.internz.ui.profile

import android.widget.CheckedTextView

object TimelineHelper {
    var count : Int = 0

    //이전의 버튼
    var lastButton : CheckedTextView? = null
    //현재 버튼
    var currentButton : CheckedTextView? = null

    var isOK : Boolean = false

    var isTitle: Boolean = false

    var startPeriod : Boolean = false

    var endPeriod: Boolean = false

    var category: Boolean = false


}