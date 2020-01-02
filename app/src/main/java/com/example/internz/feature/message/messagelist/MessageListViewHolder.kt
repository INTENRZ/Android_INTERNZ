package com.example.internz.feature.message.messagelist

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.data.message.MessageListResponseData
import com.example.internz.data.messagelist.MessageListData
import com.example.internz.feature.message.messagesend.MessageSendActivity

class MessageListViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val view : View = view.findViewById(R.id.rvListItem)

    private val meOrNot : String = "" //보낸 or 받은 쪽지 판단
    private val txtSendOrReceive : TextView = view.findViewById(R.id.txtListItemSendOrReceive)
    private val txtContents : TextView = view.findViewById(R.id.txtListItemContents)
    private val date : TextView = view.findViewById(R.id.txtListItemDate)
    private val time : TextView = view.findViewById(R.id.txtListItemTime)

    fun bind(data : MessageListResponseData) {
        //보낸쪽지 or 받은쪽지 판단
        if(data.receiver.equals(ApiServiceImpl.getUserIdx())) {
            txtSendOrReceive.text = "보낸 쪽지"
            txtSendOrReceive.setTextColor(Color.parseColor("#ffc200"))
        }
        else {
            txtSendOrReceive.text = "받은 쪽지"
            txtSendOrReceive.setTextColor(Color.parseColor("#03b462"))
        }

        txtContents.text = data.content

        //TODO! 날짜 및 시간 제대로 출력되는지 확인
        date.text = data.date.replace("-",".").substring(2,10)
        time.text = data.date.substring(12,16)

        //click listener 지정
        view.setOnClickListener {
            val intent = Intent(view.context, MessageSendActivity::class.java)
            view.context.startActivity(intent)
        }
    }
}