package com.example.internz.feature.jobselect

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckedTextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.jobselect.JobSelectItem
import com.example.internz.feature.SelectHelper
import com.example.internz.feature.setprofile.SetProfileActivity
import kotlinx.android.synthetic.main.activity_job_select.*

class JobSelectActivity : AppCompatActivity() {
    private lateinit var recylerView : RecyclerView
    private lateinit var adapter : JobSelectAdapter
    private lateinit var activity : Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_select)

        jobSelectFunction()
    }

    private fun jobSelectFunction() {
        activity = this

        //변수 초기화
        recylerView = findViewById(R.id.rvJobSelect)
        adapter = JobSelectAdapter(this)

        recylerView.adapter = adapter
        recylerView.layoutManager = GridLayoutManager(this, 4)

        //데이터 노가다
        adapter.data = listOf(
            JobSelectItem("기획"),
            JobSelectItem("인사"),
            JobSelectItem("회계"),
            JobSelectItem("교육"),
            JobSelectItem("구매"),
            JobSelectItem("홍보"),
            JobSelectItem("광고"),
            JobSelectItem("국내영업"),
            JobSelectItem("해외영업"),
            JobSelectItem("영업지원"),
            JobSelectItem("마케팅"),
            JobSelectItem("서비스"),
            JobSelectItem("상품개발"),
            JobSelectItem("공정"),
            JobSelectItem("생산"),
            JobSelectItem("품질"),
            JobSelectItem("유통"),
            JobSelectItem("연구개발"),
            JobSelectItem("디자인"),
            JobSelectItem("무역"),
            JobSelectItem("IT개발"),
            JobSelectItem("공무원"),
            JobSelectItem("리서치"),
            JobSelectItem("컨설팅"),
            JobSelectItem("기타")
        )

        adapter.notifyDataSetChanged()

        //다음(click_event)
        btnJobSelectNext.setOnClickListener {
            if(SelectHelper.count == 3) {
                val intent = Intent(this, SetProfileActivity::class.java)
                startActivity(intent)

            } else {
                Toast.makeText(this, "세 개의 관심직무를 선택하세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    inner class JobSelectAdapter(private val context : Context) : RecyclerView.Adapter<JobSelectViewHolder>() {
        var data = listOf<JobSelectItem>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobSelectViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.rv_job_select_item, parent, false)
            return JobSelectViewHolder(view)
        }

        override fun onBindViewHolder(holder: JobSelectViewHolder, position: Int) {
            holder.bind(data[position])
        }

        override fun getItemCount(): Int {
            return data.size
        }
    }

    inner class JobSelectViewHolder(view : View) : RecyclerView.ViewHolder(view) {

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

                        if (SelectHelper.count == 3) {
                            activity.findViewById<Button>(R.id.btnJobSelectNext).setBackgroundResource(R.drawable.btn_shape_ok)
                        } else {
                            activity.findViewById<Button>(R.id.btnJobSelectNext).setBackgroundResource(R.drawable.btn_shape)
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

                        if (SelectHelper.count == 3) {
                            activity.findViewById<Button>(R.id.btnJobSelectNext).setBackgroundResource(R.drawable.btn_shape_ok)
                        } else {
                            activity.findViewById<Button>(R.id.btnJobSelectNext).setBackgroundResource(R.drawable.btn_shape)
                        }
                    }
                }
                Log.e("TAG", "선택된 관심 직무 : ${SelectHelper.arrayList}")

            }
        }
    }
}
