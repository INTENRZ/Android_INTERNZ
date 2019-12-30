package com.example.internz.ui.profile

import android.graphics.Color
import android.view.View
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.timeline.TimelineCategoryData
import com.example.internz.feature.jobselect.SelectHelper

class TimelineCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val category: CheckedTextView = itemView.findViewById(R.id.checktxt_filter)
    val adapter: TimelineCategoryAdapter = TimelineCategoryAdapter(itemView.context)

    fun bind(data: TimelineCategoryData){
        category.text = data.category
    }

    fun changeColor(){
        itemView.setOnClickListener {

            // default가 false
            category.toggle()


            // 글자 색도 바꾸기
            if(category.isChecked == true){
                //TODO(하나만 노란색이 되게 해야함)

                category.setTextColor(Color.parseColor("#ffc200"))
            }else{
                category.setTextColor(Color.parseColor("#212529"))
            }

        }

    }
    
}