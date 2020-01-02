package com.example.internz.feature.homerecomm

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.internz.R
import com.example.internz.data.home.RecommProfileData
import com.example.internz.ui.profile.main.OtherProfileActivity

class HomerecommViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
    val img_thumbNail : ImageView = itemView.findViewById(R.id.img_itemRecommProfile_thumbnail)
    val txt_name : TextView = itemView.findViewById(R.id.txt_itemRecommProfile_name)
    val txt_introduce : TextView = itemView.findViewById(R.id.txt_itemRecommProfile_introduce)
    val adapter = HomerecommAdapter(itemView.context)

    fun bind(data : RecommProfileData){
        txt_name.text = data.nickname
        txt_introduce.text = data.introduce

//        itemView.setOnClickListener{
//            val intent = Intent(itemView.context, OtherProfileActivity::class.java)
//            intent.putExtra("userIdx", data.userIdx.toString())
//            itemView.context.startActivity(intent)
//        }
    }



}