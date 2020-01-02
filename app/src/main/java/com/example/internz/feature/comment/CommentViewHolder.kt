package com.example.internz.feature.comment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.comment.CommentData
import com.example.internz.data.comment.CommentResponseData
import kotlinx.android.synthetic.main.activity_comment.view.*

class CommentViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val nickname: TextView = view.findViewById(R.id.txtCommentNick)
    private val comments : TextView = view.findViewById(R.id.txtCommentContents)
    private val date : TextView = view.findViewById(R.id.txtCommentDate)
    private val time : TextView = view.findViewById(R.id.txtCommentTime)

    fun bind(data : CommentResponseData) {
        nickname.text = data.nickname
        comments.text = data.content

        val newDate = data.date.replace("-",".")
        //날짜
        date.text = newDate.substring(5, 10)
        //시간
        time.text = newDate.substring(11)
    }
}