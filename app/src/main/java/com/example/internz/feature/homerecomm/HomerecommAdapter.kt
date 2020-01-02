package com.example.internz.feature.homerecomm

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.internz.R
import com.example.internz.data.home.RecommProfileData
import com.example.internz.ui.profile.main.OtherProfileActivity


class HomerecommAdapter(private val context : Context) : RecyclerView.Adapter<HomerecommViewHolder>() {

//    var data = listOf<RecommData>()
    var data = listOf<RecommProfileData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomerecommViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.rv_recomm_profile_item, parent, false)
        return HomerecommViewHolder(itemView)
    }

    override fun onBindViewHolder(holderMain: HomerecommViewHolder, position: Int) {
        holderMain.bind(data[position])

        if(data[position].front_image == "undefined"){
            // 프로필 이미지가 없는 경우 기본 이미지 적용
            holderMain.img_thumbNail.setImageDrawable(
                AppCompatResources.getDrawable(
                    holderMain.itemView.context,
                    R.drawable.home_recomm_profile_img
                )
            )
        }else{
            // 프로필 이미지가 있는 경우 해당 이미지 적용
            Glide
                .with(holderMain.itemView.context)
                .load(data[position].front_image)
                .apply(RequestOptions.circleCropTransform())
                .into(holderMain.img_thumbNail)
//            Log.d("chohee", data[position].front_image)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}