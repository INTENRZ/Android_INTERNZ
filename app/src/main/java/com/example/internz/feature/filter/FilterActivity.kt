package com.example.internz.feature.filter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.data.filter.FilterRequestData
import kotlinx.android.synthetic.main.activity_filter.*
import java.util.logging.Filter

class FilterActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : FilterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        filterFunction()
    }

    private fun filterFunction() {
        //변수 초기화
        recyclerView = findViewById(R.id.rvFilter)
        adapter = FilterAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 4)

        adapter.data = listOf(
            FilterRequestData("전체"),
            FilterRequestData("기획"),
            FilterRequestData("인사"),
            FilterRequestData("회계"),
            FilterRequestData("교육"),
            FilterRequestData("구매"),
            FilterRequestData("홍보"),
            FilterRequestData("광고"),
            FilterRequestData("국내영업"),
            FilterRequestData("해외영업"),
            FilterRequestData("영업지원"),
            FilterRequestData("마케팅"),
            FilterRequestData("서비스"),
            FilterRequestData("상품개발"),
            FilterRequestData("공정"),
            FilterRequestData("생산"),
            FilterRequestData("품질"),
            FilterRequestData("유통"),
            FilterRequestData("연구개발"),
            FilterRequestData("디자인"),
            FilterRequestData("무역"),
            FilterRequestData("IT개발"),
            FilterRequestData("공무원"),
            FilterRequestData("리서치"),
            FilterRequestData("컨설팅"),
            FilterRequestData("기타")
        )
        adapter.notifyDataSetChanged()

        btnFilterApply?.setOnClickListener {
            Toast.makeText(this, "필터 서버통신 필요", Toast.LENGTH_SHORT).show()
            finish()
        }

        imgFilterBack?.setOnClickListener {
            finish()
        }
    }
}
