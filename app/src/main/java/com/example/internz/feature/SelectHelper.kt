package com.example.internz.feature

import android.widget.ArrayAdapter
import android.widget.Button

object SelectHelper {
    //선택된 직무 개수
    var count = 0
    //선택된 직무 position
    var arrayList : ArrayList<String> = ArrayList<String>()

    //타임라인 카테고리 개수
    var categoryCount: IntArray = intArrayOf(0,0,0,0,0,0)

    // 타임라인 카테고리 판별
    var categoryWhat : String = ""

    var categoryOld = -1

    fun categoryCountInit(){
        categoryCount[0] = 0
        categoryCount[1] = 0
        categoryCount[2] = 0
        categoryCount[3] = 0
        categoryCount[4] = 0
        categoryCount[5] = 0
    }


}