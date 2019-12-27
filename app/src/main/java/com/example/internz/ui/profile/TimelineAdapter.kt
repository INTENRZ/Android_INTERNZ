package com.example.internz.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.ProfileTimelineData

class TimelineAdapter(private val context : Context) : RecyclerView.Adapter<TimelineVeiwHolder>() {

    var data = listOf<ProfileTimelineData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineVeiwHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.rv_profile_timeline_item, parent, false)
        return TimelineVeiwHolder(itemView)
    }

    override fun onBindViewHolder(holder: TimelineVeiwHolder, position: Int) {
        holder.bind(data[position])

        /* 세로 바 없애기 */
        // 아이템 갯수가 1개일 때
        if(itemCount == 1){
            holder.timelineIconSetting()
        }
        // 마지막 아이템일 때
        if(position == itemCount){
            holder.timelineIconSetting()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}