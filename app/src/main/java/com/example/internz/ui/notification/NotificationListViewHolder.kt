package com.example.internz.ui.notification

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.notification.NotificationListData

class NotificationListViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val view : View = view.findViewById(R.id.content)

    val rightView : View = view.findViewById(R.id.right)
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

        //recycler item click listener
        view.setOnClickListener {
            Toast.makeText(view.context, "클릭했습니다.", Toast.LENGTH_SHORT).show()
        }

        rightView.setOnClickListener {
            Toast.makeText(view.context, "추가하겠습니다.ㅎㅎ", Toast.LENGTH_SHORT).show()
        }

    }
}