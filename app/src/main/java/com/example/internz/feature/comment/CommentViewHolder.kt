package com.example.internz.feature.comment

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.internz.R
import com.example.internz.data.comment.CommentData
import com.example.internz.data.comment.CommentResponseData
import kotlinx.android.synthetic.main.activity_comment.view.*

class CommentViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val view : View = view.findViewById(R.id.rvCommentItem)

    private val image : ImageView = view.findViewById(R.id.imgCommentProfile)
    private val nickname: TextView = view.findViewById(R.id.txtCommentNick)
    private val comments : TextView = view.findViewById(R.id.txtCommentContents)
    private val date : TextView = view.findViewById(R.id.txtCommentDate)
    private val time : TextView = view.findViewById(R.id.txtCommentTime)

    fun bind(data : CommentResponseData) {
        nickname.text = data.nickname
        comments.text = data.content

        //댓글 이미지 생성
        Glide
            .with(view.context)
            .load(data.profile)
            .apply(RequestOptions.circleCropTransform())
            .into(image)

        val newDate = data.date.replace("-",".")
        //날짜
        date.text = newDate.substring(5, 10)
        //시간
        time.text = newDate.substring(11)
    }
}