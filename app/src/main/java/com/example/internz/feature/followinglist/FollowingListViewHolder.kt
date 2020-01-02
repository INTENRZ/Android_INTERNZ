package com.example.internz.feature.followinglist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.internz.R
import com.example.internz.data.follow.FollowingResponseData

class FollowingListViewHolder (view : View) : RecyclerView.ViewHolder(view) {


    private val name : TextView = view.findViewById(R.id.txtFollowingNick)
    private val desc : TextView = view.findViewById(R.id.txtFollowingContents)
    private val profileImg : ImageView = view.findViewById(R.id.imgFollowingUser)


    fun bind(responseData : FollowingResponseData) {
        name.text = responseData.name
        desc.text = responseData.desc

        if(responseData.profileImg == "undefined"){
            // 프로필 설정 이미지가 없을 경우 기본 이미지 지정
            //profileImg.setImageDrawable(R.drawable.basicprofile_img)
        }else{
            Glide
                .with(itemView)
                .load(responseData.profileImg)
                .apply(RequestOptions.circleCropTransform())
                .into(profileImg)

        }

    }
}