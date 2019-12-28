package com.example.internz.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.ui.profile.main.OtherProfileActivity
import com.example.internz.R
import com.example.internz.data.home.RecommData

class MainHomeAdapter(private val context : Context) : RecyclerView.Adapter<MainHomeViewHolder>() {

    var data = listOf<RecommData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHomeViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.rv_recomm_profile_item, parent, false)
        return MainHomeViewHolder(itemView)
    }

    override fun onBindViewHolder(holderMain: MainHomeViewHolder, position: Int) {
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