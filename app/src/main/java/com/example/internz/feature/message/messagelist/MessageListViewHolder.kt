package com.example.internz.feature.message.messagelist

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.messagelist.MessageListData
import com.example.internz.feature.message.messagesend.MessageSendActivity

class MessageListViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val view : View = view.findViewById(R.id.rvListItem)

    private val txtSendOrReceive : TextView = view.findViewById(R.id.txtListItemSendOrReceive)
    private val txtContents : TextView = view.findViewById(R.id.txtListItemContents)
    private val date : TextView = view.findViewById(R.id.txtListItemDate)
    private val time : TextView = view.findViewById(R.id.txtListItemTime)

    fun bind(data : MessageListData) {
        txtSendOrReceive.text = data.sendOrReceive
        txtContents.text = data.contents
        date.text = data.date
        time.text = data.time

        //click listener 지정
        view.setOnClickListener {
            val intent = Intent(view.context, MessageSendActivity::class.java)
            view.context.startActivity(intent)
        }
    }
}