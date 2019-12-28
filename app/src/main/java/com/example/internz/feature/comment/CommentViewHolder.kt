package com.example.internz.feature.comment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.comment.CommentData
import kotlinx.android.synthetic.main.activity_comment.view.*

class CommentViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val nickname: TextView = view.findViewById(R.id.txtCommentNick)
    private val comments : TextView = view.findViewById(R.id.txtCommentContents)
    private val date : TextView = view.findViewById(R.id.txtCommentDate)
    private val time : TextView = view.findViewById(R.id.txtCommentTime)

    fun bind(data : CommentData) {
        nickname.text = data.nickname
        comments.text = data.comments
        date.text = data.date
        time.text = data.date
    }
}