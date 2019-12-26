package com.example.internz.feature.notification

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.NotificationListData

class NotificationListViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    val photo : ImageView = view.findViewById(R.id.imgNotilistImg)
    val title : TextView = view.findViewById(R.id.txtNotilistTitle)
    val dday : TextView = view.findViewById(R.id.txtNotilistDday)
    val desc : TextView = view.findViewById(R.id.txtNotilistDesc)

    fun bind(data : NotificationListData)
    {
        title.text = data.title
        dday.text = data.dday
        desc.text = data.desc
        photo.setImageDrawable(ResourcesCompat.getDrawable(itemView.resources, data.img, null))
    }
}