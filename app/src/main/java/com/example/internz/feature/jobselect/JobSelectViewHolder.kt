package com.example.internz.feature.jobselect

import android.util.Log
import android.view.View
import android.widget.CheckedTextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.jobselect.JobSelectItem

class JobSelectViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    private val view : View = view.findViewById(R.id.rvJobSelectItem)

    private val job : CheckedTextView = view.findViewById(R.id.rvJobSelectBtn)

    fun bind(data: JobSelectItem) {
        job.text = data.job

        job.setOnClickListener {
            when(job.isSelected) {
                true -> {
                    job.isSelected = false
                    JobSelectHelper.count--
                    JobSelectHelper.arrayList.remove(job.text.toString())
                }
                false -> {
                    if(JobSelectHelper.count < 3) {
                        job.isSelected = true
                        JobSelectHelper.count++
                        JobSelectHelper.arrayList.add(job.text.toString())
                    } else {
                        Toast.makeText(view.context, "세 개의 직무를 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            Log.e("TAG", "선택된 관심 직무 : ${JobSelectHelper.arrayList}")
        }
    }
}