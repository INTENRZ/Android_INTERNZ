package com.example.internz.feature

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.internz.R
import com.example.internz.feature.jobselect.SetProfileActivity
import kotlinx.android.synthetic.main.activity_job_select.*

class JobSelectActivity : AppCompatActivity() {

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_select)

        jobSelectFunction()
    }

    private fun jobSelectFunction() {
        //val listener 객체 전달
        val listener = object : View.OnClickListener {
            override fun onClick(p0: View?) {
                when (p0?.id) {
                    R.id.btnJob1 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob2 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob3 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob4 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob5 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob6 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob7 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob8 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob9 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob10 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob11 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob12 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob13 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob14 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob15 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob16 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob17 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob18 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob19 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob20 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob21 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob22 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob23 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob24 -> {
                        btnFunction(p0)
                    }
                    R.id.btnJob25 -> {
                        btnFunction(p0)
                    }
                }
            }
        }

        setClickListener(listener)

        btnJobSelectNext.setOnClickListener {
            if (count == 3) {
                val intent = Intent(applicationContext, SetProfileActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "세 개의 관심직무를 선택해야 합니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun btnFunction(view : View) {
        val btn = findViewById<Button>(view?.id)
        if(btn.isPressed) {
            if(count < 3) {
                btn.setBackgroundResource(R.drawable.btn_for_job_selected)
            }
            else {
                Toast.makeText(applicationContext, "최대 세 개의 관심직무를 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()

            }
            count--
        }
        else if (btn.isSelected){
            btn.setBackgroundResource(R.drawable.btn_for_job_select)
        }
    }

    private fun setClickListener(listener : View.OnClickListener) {
        btnJob1.setOnClickListener(listener)
        btnJob2.setOnClickListener(listener)
        btnJob3.setOnClickListener(listener)
        btnJob4.setOnClickListener(listener)
        btnJob5.setOnClickListener(listener)
        btnJob6.setOnClickListener(listener)
        btnJob7.setOnClickListener(listener)
        btnJob8.setOnClickListener(listener)
        btnJob9.setOnClickListener(listener)
        btnJob10.setOnClickListener(listener)
        btnJob11.setOnClickListener(listener)
        btnJob12.setOnClickListener(listener)
        btnJob13.setOnClickListener(listener)
        btnJob14.setOnClickListener(listener)
        btnJob15.setOnClickListener(listener)
        btnJob16.setOnClickListener(listener)
        btnJob17.setOnClickListener(listener)
        btnJob18.setOnClickListener(listener)
        btnJob19.setOnClickListener(listener)
        btnJob20.setOnClickListener(listener)
        btnJob21.setOnClickListener(listener)
        btnJob22.setOnClickListener(listener)
        btnJob23.setOnClickListener(listener)
        btnJob24.setOnClickListener(listener)
        btnJob25.setOnClickListener(listener)

    }
}
