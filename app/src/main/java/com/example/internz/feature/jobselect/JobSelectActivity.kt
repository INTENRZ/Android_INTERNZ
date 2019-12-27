package com.example.internz.feature.jobselect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.data.jobselect.JobSelectData
import com.example.internz.data.jobselect.JobSelectItem
import com.example.internz.data.jobselect.JobSelectPutData
import com.example.internz.feature.SetProfileActivity
import kotlinx.android.synthetic.main.activity_job_select.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class JobSelectActivity : AppCompatActivity() {
    private lateinit var recylerView : RecyclerView
    private lateinit var adapter : JobSelectAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_select)

        jobSelectFunction()
    }

    private fun jobSelectFunction() {
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
            if(JobSelectHelper.count == 3) {
                //서버 통신
                val call = ApiServiceImpl.service.putJobSelect(
                    JobSelectPutData(JobSelectHelper.arrayList.get(0),
                        JobSelectHelper.arrayList.get(1),
                        JobSelectHelper.arrayList.get(2)))

                call.enqueue(
                    object : retrofit2.Callback<JobSelectData> {
                        override fun onFailure(call: Call<JobSelectData>, t: Throwable) {
                            Log.e("TAG", "JobSelectActiviy 서버 통신 불가")
                        }

                        override fun onResponse(
                            call: Call<JobSelectData>,
                            response: Response<JobSelectData>
                        ) {
                            if (response.isSuccessful) {
                                if(response.body()?.status!!) {
                                    val intent = Intent(applicationContext, SetProfileActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    Log.e("TAG", response.body()?.message.toString())
                                }
                            }
                            else {
                                //서버 통신 불가
                            }
                        }
                    }
                )

                val intent = Intent(this, SetProfileActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "세 개의 관심직무를 선택하세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
