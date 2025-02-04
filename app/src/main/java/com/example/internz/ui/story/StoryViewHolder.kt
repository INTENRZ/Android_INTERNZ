package com.example.internz.ui.story

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.story.StoryCategoryResponseData
import com.example.internz.ui.story.detailstory.DetailStoryActivity

class StoryViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val view : View = view.findViewById(R.id.rvStoryItem)

    private val title : TextView = view.findViewById(R.id.txtStoryTitle)
    private val nickname : TextView = view.findViewById(R.id.txtStoryNick)
    private val date : TextView = view.findViewById(R.id.txtStoryDate)

    fun bind(data : StoryCategoryResponseData) {
        //통신
        title.text = data.title
        nickname.text = data.nickname
        date.text = data.date.replace("-",".")
    }
}