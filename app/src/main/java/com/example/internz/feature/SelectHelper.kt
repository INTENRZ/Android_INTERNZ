package com.example.internz.feature

import android.widget.Button

object SelectHelper {
    //선택된 직무 개수
    var count = 0
    //선택된 직무 position
    var arrayList : ArrayList<String> = ArrayList<String>()

    //타임라인 카테고리 개수
    var categoryCount = 0

    // 타임라인 카테고리 판별
    var categoryWhat = -1
}