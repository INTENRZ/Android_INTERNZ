package com.example.internz.feature.homerecomm

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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

        holderMain.itemView.setOnClickListener{
            val intent = Intent(context, OtherProfileActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}