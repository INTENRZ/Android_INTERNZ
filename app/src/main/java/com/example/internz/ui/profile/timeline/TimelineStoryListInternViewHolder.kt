package com.example.internz.ui.profile.timeline

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.profile.TimelineStoryListInternData
import com.example.internz.ui.story.StoryHelper
import com.example.internz.ui.story.detailstory.DetailStoryActivity

class TimelineStoryListInternViewHolder (view : View) : RecyclerView.ViewHolder(view) {

    val title : TextView = view.findViewById(R.id.txtTimelineStoryListInternTitle)
    val date : TextView = view.findViewById(R.id.txtTimelineStoryListInternDate)
    val img : ImageView = view.findViewById(R.id.imgTimelineStoryListInternImg)
    val bar : View = view.findViewById(R.id.bar)
    val timelineStoryListInternAdapter = TimelineStoryListInternAdapter(itemView.context)

    fun bind(internData : TimelineStoryListInternData)
    {

        /* 서버에서 받은 데이터 중 isMe 가 데이터에 들어가 빈 리사이클러뷰가 생기는 경우 방지 */
//        if(adapterPosition == ){
//            title.text = ""
//            date.text = ""
//            img.visibility = View.INVISIBLE
//            bar.visibility = View.INVISIBLE
//        }else{
            title.text = internData.title
            date.text = internData.updated_date
//        }

        itemView.setOnClickListener {
            val intent = Intent(itemView.context, DetailStoryActivity::class.java)
            Log.d("chohee", "눌림")
            //Log.d("chohee", timelineStoryListInternAdapter.data[adapterPosition].storyIdx.toString())
            StoryHelper.setStoryIndex(internData.storyIdx.toString())
            itemView.context.startActivity(intent)
        }

    }
}