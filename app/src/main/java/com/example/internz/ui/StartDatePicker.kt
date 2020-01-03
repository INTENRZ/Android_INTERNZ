package com.example.internz.ui

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.internz.R
import com.example.internz.ui.profile.TimelineHelper
import kotlinx.android.synthetic.main.activity_timeline_add.*
import kotlinx.android.synthetic.main.activity_timeline_add.view.*
import java.util.*

class StartDatePicker : DialogFragment(), DatePickerDialog.OnDateSetListener {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(activity!!, this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val btn_startDate : TextView = activity!!.findViewById(R.id.btn_startDate)
        val addBtn : TextView = activity!!.findViewById(R.id.txt_timelineadd_add)
        DatePickderHelper.startDate = "${year}-${month+1}-${day}"
        btn_startDate.text = DatePickderHelper.startDate

        /** 모든 정보가 입력된 경우에만 등록버튼 활성화하기 위한 코드 */
        if(TimelineHelper.isTitle != true && DatePickderHelper.startDate != "" && DatePickderHelper.endDate != "" && TimelineHelper.category != false){
            addBtn.setTextColor(Color.parseColor("#ffc200"))
            TimelineHelper.isOK = true
        }else{
            addBtn.setTextColor(Color.parseColor("#dbdbdb"))
            TimelineHelper.isOK = false
        }
    }


}

