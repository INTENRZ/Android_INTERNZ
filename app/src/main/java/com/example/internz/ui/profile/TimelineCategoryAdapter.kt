package com.example.internz.ui.profile

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.timeline.TimelineCategoryData
import com.example.internz.feature.jobselect.SelectHelper

class TimelineCategoryAdapter(private val context: Context) : RecyclerView.Adapter<TimelineCategoryViewHolder>(){

    var data = listOf<TimelineCategoryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineCategoryViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rv_timeline_category_item, parent, false)
        return TimelineCategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: TimelineCategoryViewHolder, position: Int) {
        holder.bind(data[position])
        holder.category.setOnClickListener{
            //맨 처음 누르면 false임
            /* 카테고리 셀렉터 설정 */
            when(holder.category.isSelected) {
                // 이미 선택되어 있는 카테고리를 선택할 경우
                true -> {
                    holder.category.isSelected = false
                    SelectHelper.categoryCount = 0
                    holder.category.setTextColor(Color.parseColor("#212529"))
                }
                // 선택되어 있지 않은 카테고리를 선택할 경우
                false -> {
                    holder.category.isSelected = true
                    SelectHelper.categoryCount++

                    if(SelectHelper.categoryCount > 1){
                        holder.category.isSelected = false
                        holder.category.setTextColor(Color.parseColor("#212529"))
                        Toast.makeText(context, "한 개의 카테고리를 선택해주세요.", Toast.LENGTH_SHORT).show()
                    }else{
                        holder.category.setTextColor(Color.parseColor("#ffc200"))
                    }

                }
            }

        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

}