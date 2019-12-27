package com.example.internz.ui.Story

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.story.StoryData
import org.w3c.dom.Text

class StoryViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val view : View = view.findViewById(R.id.rvStoryItem)

    private val title : TextView = view.findViewById(R.id.txtStoryTitle)
    private val nickname : TextView = view.findViewById(R.id.txtStoryNick)
    private val date : TextView = view.findViewById(R.id.txtStoryDate)

    fun bind(data : StoryData) {
        title.text = data.title
        nickname.text = data.nickname
        date.text = data.date

        //TODO! clickEvent 구현
        view.setOnClickListener {
            //서버와 통신해서 스토리 데이터 받아와서 보여줘야 함

        }
    }
}