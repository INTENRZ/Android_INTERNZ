package com.example.internz.feature.filter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import com.example.internz.common.toast
import com.example.internz.data.filter.FilterItem
import com.example.internz.ui.notification.NotificationFragment
import com.example.internz.ui.notification.NotificationListAdapter
import com.example.internz.ui.story.StoryFragment
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : FilterAdapter
    private lateinit var activity : Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        filterFunction()
    }

    private fun filterFunction() {
        activity = this

        //변수 초기화
        recyclerView = findViewById(R.id.rvFilter)
        adapter = FilterAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 4)

        //recyclerview 데이터 setting
        adapter.data = listOf(
            FilterItem("전체"),
            FilterItem("기획"),
            FilterItem("인사"),
            FilterItem("회계"),
            FilterItem("교육"),
            FilterItem("구매"),
            FilterItem("홍보"),
            FilterItem("광고"),
            FilterItem("국내영업"),
            FilterItem("해외영업"),
            FilterItem("영업지원"),
            FilterItem("마케팅"),
            FilterItem("서비스"),
            FilterItem("상품개발"),
            FilterItem("공정"),
            FilterItem("생산"),
            FilterItem("품질"),
            FilterItem("유통"),
            FilterItem("연구개발"),
            FilterItem("디자인"),
            FilterItem("무역"),
            FilterItem("IT개발"),
            FilterItem("공무원"),
            FilterItem("리서치"),
            FilterItem("컨설팅"),
            FilterItem("기타")
        )
        adapter.notifyDataSetChanged()

        btnFilterApply?.setOnClickListener {

            if (FilterHelper.count < 1) {
                Toast.makeText(this, "1개의 필터를 선택해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else {
                setResult(Activity.RESULT_OK)

                FilterHelper.count = 0
                finish()
            }
        }

        imgFilterBack?.setOnClickListener {
            finish()
        }
    }

    inner class FilterAdapter(private val context : Context) : RecyclerView.Adapter<FilterViewHolder>() {
        var data = listOf<FilterItem>()
//        lateinit var v : View //findV를 사용할 수 있는 recycler view

        override fun getItemCount(): Int {
            return data.size
        }

        override fun onBindViewHolder(holder: FilterViewHolder, position: Int) {
            holder.bind(data[position])

            holder.itemView.setOnClickListener {
                val checkedTextView = holder.itemView.findViewById<CheckedTextView>(R.id.rvFilterTxt)

                //내가 선택한 CheckedTextView의 정보 저장
                if(FilterHelper.count == 0) { //사용자가 선택한 필터가 없는 경우, current == null
                    FilterHelper.currentButton = checkedTextView //CheckedTextView 정보 저장
                    FilterHelper.count++ //count 증가
                    FilterHelper.currentButton?.toggle() //drawable 변경
                    FilterHelper.currentButton?.setTextColor(Color.parseColor("#ffc200")) //텍스트 색상 변경(YELLOW)
                }
                else if (FilterHelper.count == 1){ //사용자가 선택한 필터가 있는 경우, current != null
                    if(FilterHelper.currentButton!!.equals(checkedTextView)) { //사용자가 선택한 필터가 현재 선택된 필터인 경우
                        FilterHelper.count--
                        FilterHelper.currentButton?.toggle() //drawable 변경
                        FilterHelper.currentButton?.setTextColor(Color.parseColor("#000000")) //텍스트 색상 변경(BLACK)
                        FilterHelper.currentButton = null //현재 선택된 버튼 초기화
                    } else { //사용자가 선택한 필터가 이전과 다른 경우 //현재와 다른 필터를 선택한 경우
                        FilterHelper.currentButton?.toggle() //현재로 저장된 이전 필터 drawable 변경
                        FilterHelper.currentButton?.setTextColor(Color.parseColor("#000000")) //텍스트 색상 변경(BLACK)
                        FilterHelper.lastButton = FilterHelper.currentButton //현재 필터를 이전 필터로 변경
                        //카운트는 동일하게 1로 유지
                        FilterHelper.currentButton = checkedTextView
                        FilterHelper.currentButton?.toggle() //drawable 변경
                        FilterHelper.currentButton?.setTextColor(Color.parseColor("#ffc200")) //텍스트 색상 변경(YELLOW)
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.rv_filter_item, parent, false)
//            this.v = view //findV를 수행할 view 저장

            return FilterViewHolder(view)
        }
    }

    inner class FilterViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private val view : View = view.findViewById(R.id.rvFilterItem)

        private val button : CheckedTextView = view.findViewById(R.id.rvFilterTxt)

        fun bind(data: FilterItem) {
            button.text = data.filter
        }
    }
}
