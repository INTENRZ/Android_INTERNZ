package com.example.internz.feature.message.messagelist

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.messagelist.MessageListData

class MessageListViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val txtSendOrReceive : TextView = view.findViewById(R.id.txtListItemSendOrReceive)
    private val txtContents : TextView = view.findViewById(R.id.txtListItemContents)
    private val date : TextView = view.findViewById(R.id.txtListItemDate)
    private val time : TextView = view.findViewById(R.id.txtListItemTime)

    fun bind(data : MessageListData) {
        txtSendOrReceive.text = data.sendOrReceive
        txtContents.text = data.contents
        date.text = data.date
        time.text = data.time
    }
}