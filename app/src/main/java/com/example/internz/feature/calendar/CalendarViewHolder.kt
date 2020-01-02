package com.example.internz.feature.calendar

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.internz.R
import com.example.internz.data.calendar.CalendarData
import com.example.internz.data.calendar.CalenderResponseData

class CalendarViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val view : View = view.findViewById(R.id.rvCalendarItem)

    //TODO! 색상 및 이미지를 우리가 결정하는것으로 변경
    private val logo : ImageView = view.findViewById(R.id.imgCalendarLogo)
    private val name : TextView = view.findViewById(R.id.imgCalendarName)
    private val team : TextView = view.findViewById(R.id.imgCalendarTeam)
    private val date : TextView = view.findViewById(R.id.txtCalendarDate)

    fun bind(data : CalenderResponseData) {
        name.text = data.company //회사 이름
        team.text = data.team //팀 이름
        //날짜
        if (data.day < 0) {
            date.text = "D" + data.day.toString()
        } else if (data.day == 0){ 
            date.text = "D-DAY"
        }
        else {
            date.text = "D+" + data.day.toString()
        }
        //회사 로고
        Glide.with(view.context).load(data.logo).into(logo)
    }
}