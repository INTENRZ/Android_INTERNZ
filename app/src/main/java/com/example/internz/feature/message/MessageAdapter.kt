package com.example.internz.feature.message

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.data.message.MessageResponseData
import com.example.internz.data.messagelist.MessageData
import com.example.internz.feature.message.messagelist.MessageListActivity

class MessageAdapter(private val context : Context) : RecyclerView.Adapter<MessageViewHolder>() {
    var data = listOf<MessageResponseData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_message_item, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(data[position])

        holder.itemView?.setOnClickListener {
            val intent = Intent(holder.itemView.context, MessageListActivity::class.java)
                .putExtra("userIndex", data[position].uesrIdx.toString()) //유저인덱스 넘김
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}