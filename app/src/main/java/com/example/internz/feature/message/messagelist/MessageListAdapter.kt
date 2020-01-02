package com.example.internz.feature.message.messagelist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.message.MessageListResponseData
import com.example.internz.data.messagelist.MessageListData

class MessageListAdapter(private val context : Context) : RecyclerView.Adapter<MessageListViewHolder>() {
    var data = listOf<MessageListResponseData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_message_list_item, parent, false)
        return MessageListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}