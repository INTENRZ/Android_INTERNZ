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
        //상대방(receiver)의 index = ApiServiceImp.getReceiverIndex()
        val other = ApiServiceImpl.getReceiverIdx() //내가 아닌 상대방의 인덱스
        // sender == other일 경우 받은 쪽지, 아닐 경우 보낸 쪽지

        //보낸쪽지 or 받은쪽지 판단
        if(data.sender.toString().equals(other)) { //받은 쪽지
            txtSendOrReceive.text = "받은 쪽지"
            txtSendOrReceive.setTextColor(Color.parseColor("#ffc200"))
        } else { //보낸 쪽지
            txtSendOrReceive.text = "보낸 쪽지"
            txtSendOrReceive.setTextColor(Color.parseColor("#03b462"))
        }

        //쪽지의 내용
        txtContents.text = data.content

        //날짜 및 시간 형식 변환
        date.text = data.date.replace("-",".").substring(2,10)
        time.text = data.date.substring(11,16)
    }
}