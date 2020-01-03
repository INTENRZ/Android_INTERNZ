package com.example.internz.ui

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.internz.R
import kotlinx.android.synthetic.main.activity_timeline_add.*
import java.util.*

class EndDatePicker : DialogFragment(), DatePickerDialog.OnDateSetListener {

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
        val btn_endDate : TextView = activity!!.findViewById(R.id.btn_endDate)
        DatePickderHelper.endDate = "${year}-${month+1}-${day}"
        btn_endDate.text = DatePickderHelper.endDate
    }


}

