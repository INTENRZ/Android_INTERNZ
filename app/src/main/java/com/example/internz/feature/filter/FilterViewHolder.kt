package com.example.internz.feature.filter

import android.graphics.Color
import android.view.View
import android.widget.CheckedTextView
import android.widget.Filter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.filter.FilterRequestData
import com.example.internz.feature.jobselect.SelectHelper

class FilterViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val view : View = view.findViewById(R.id.rvFilterItem)

    private val button : CheckedTextView = view.findViewById(R.id.rvFilterTxt)

    fun bind(data: FilterRequestData) {
        button.text = data.filter

        button?.setOnClickListener {
            when (button.isChecked) {
                true -> {
                    button.toggle()
                    button.setTextColor(Color.parseColor("#000000"))
                    FilterHelper.count--
                    FilterHelper.filterText = null
                }
                false -> {
                    if (FilterHelper.count < 1) {
                        button.toggle()
                        button.setTextColor(Color.parseColor("#ffc200"))
                        FilterHelper.count++
                        FilterHelper.filterText = button.text.toString()
                    } else {
                        Toast.makeText(view.context, "세 개의 직무를 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


}