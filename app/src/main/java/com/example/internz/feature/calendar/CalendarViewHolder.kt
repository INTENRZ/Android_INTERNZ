package com.example.internz.feature.calendar

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.calendar.CalendarData

class CalendarViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val view : View = view.findViewById(R.id.rvCalendarItem)

    //TODO! 색상 및 이미지를 우리가 결정하는것으로 변경
    private val color : ImageView = view.findViewById(R.id.imgCalendarColor)
    private val name : TextView = view.findViewById(R.id.imgCalendarName)
    private val team : TextView = view.findViewById(R.id.imgCalendarTeam)
    private val date : TextView = view.findViewById(R.id.txtCalendarDate)

    fun bind(data : CalendarData) {
        name.text = data.name
        team.text = data.team
        date.text = data.date
    }
}