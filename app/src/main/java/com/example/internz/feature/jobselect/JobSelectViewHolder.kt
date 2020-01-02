package com.example.internz.feature.jobselect

import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckedTextView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.jobselect.JobSelectItem
import com.example.internz.feature.SelectHelper

class JobSelectViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    private val view : View = view.findViewById(R.id.rvJobSelectItem)

    private val job : CheckedTextView = view.findViewById(R.id.rvJobSelectBtn)

    fun bind(data: JobSelectItem) {
        job.text = data.job

        job.setOnClickListener {
            when (job.isChecked) {
                true -> {
                    job.toggle()
                    job.setTextColor(Color.parseColor("#000000"))
                    SelectHelper.count--
                    SelectHelper.arrayList.remove(job.text.toString())

                    //TODO! 숫자가 3개라면 백그라운드 색상 변환
                    if (SelectHelper.count == 3) {
                        view.findViewById<Button>(R.id.btnJobSelectNext).setBackgroundResource(R.drawable.btn_shape_ok)
                    }
                }
                false -> {
                    if (SelectHelper.count < 3) {
                        job.toggle()
                        job.setTextColor(Color.parseColor("#ffc200"))
                        SelectHelper.count++
                        SelectHelper.arrayList.add(job.text.toString())
                    } else {
                        Toast.makeText(view.context, "세 개의 직무를 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            Log.e("TAG", "선택된 관심 직무 : ${SelectHelper.arrayList}")

        }
    }
}