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
            holder.itemView.context.startActivity(intent)

            //TODO! useridx에서 receiver인덱스로 변환함
            ApiServiceImpl.setReceiverIdx(data[position].uesrIdx.toString())
            Log.e("TAG", "특정인과의 메시지 띄울때 상대방(receiver)의 userIdx : ${ApiServiceImpl.getReceiverIdx()}")
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}