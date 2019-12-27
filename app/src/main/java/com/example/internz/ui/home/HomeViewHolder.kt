package com.example.internz.ui.home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.home.RecommData

class HomeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val img_thumbNail : ImageView = itemView.findViewById(R.id.img_itemRecommProfile_thumbnail)
    val txt_name : TextView = itemView.findViewById(R.id.txt_itemRecommProfile_name)
    val txt_introduce : TextView = itemView.findViewById(R.id.txt_itemRecommProfile_introduce)

    fun bind(data : RecommData){
        txt_name.text = data.txt_name
        txt_introduce.text = data.txt_introduce
    }
}