package com.example.internz.feature.message

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.internz.R
import com.example.internz.data.message.MessageResponseData
import com.example.internz.data.messagelist.MessageData
import com.example.internz.feature.message.messagelist.MessageListActivity
import kotlinx.android.synthetic.main.rv_message_item.view.*

class MessageViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val view : View = view.findViewById<ImageView>(R.id.rvMessageItem)

    private val imageView = view.findViewById<ImageView>(R.id.imgMessageUser)
    private val nickname = view.findViewById<TextView>(R.id.txtMessageNick)
    private val contents = view.findViewById<TextView>(R.id.txtMessageContents)

    fun bind(data : MessageResponseData) {
        nickname.text = data.nickname
        contents.text = data.content
        Glide
            .with(view.context)
            .load(data.image)
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)
    }
}