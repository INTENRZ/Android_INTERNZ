package com.example.internz.ui.profile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.BaseResponse
import com.example.internz.common.enqueue
import com.example.internz.common.toast
import com.example.internz.data.profile.TimelineAddRequestData
import com.example.internz.data.timeline.TimelineCategoryData
import com.example.internz.feature.SelectHelper
import com.example.internz.ui.DatePickderHelper
import com.example.internz.ui.EndDatePicker
import com.example.internz.ui.StartDatePicker
import com.example.internz.feature.filter.FilterHelper
import kotlinx.android.synthetic.main.activity_timeline_add.*
import retrofit2.Call

class TimelineAddActivity : AppCompatActivity() {

    private lateinit var rv_timeline_category: RecyclerView
    private lateinit var adapter_timeline_category : TimelineCategoryAdapter

    // 서버에 넘겨줄 데이터들 선언
    private lateinit var title : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline_add)
        val et_title = findViewById<EditText>(R.id.edt_timelineadd_title)

        timelineCategoryRv()


        /* 타이틀이 작성되었는지 작성되지 않았는지 editText의 변화 확인 */
        edt_timelineadd_title.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }


            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TimelineHelper.isTitle = edt_timelineadd_title.text.isEmpty()

                /** 모든 정보가 입력된 경우에만 등록버튼 활성화하기 위한 코드 */
                if(TimelineHelper.isTitle != true && DatePickderHelper.startDate != "" && DatePickderHelper.endDate != "" && TimelineHelper.category != false){
                    txt_timelineadd_add.setTextColor(Color.parseColor("#ffc200"))
                    TimelineHelper.isOK = true
                }else{
                    txt_timelineadd_add.setTextColor(Color.parseColor("#dbdbdb"))
                    TimelineHelper.isOK = false
                }
            }
        })


        /* 등록버튼 클릭시 edittext에 작성한 텍스트 받기 + 서버 통신 */
        txt_timelineadd_add.setOnClickListener {
            if(TimelineHelper.isOK == true){
                // 타임라인 추가하기 위해 서버에 넘겨줄 데이터들
                title = et_title.text.toString()

                /* 타임라인 추가 서버 요청 */
                val call: Call<BaseResponse<TimelineAddRequestData>> = ApiServiceImpl.service.requestTimelineAdd(ApiServiceImpl.getToken(), TimelineAddRequestData(
                    title,
                    DatePickderHelper.startDate,
                    DatePickderHelper.endDate,
                    SelectHelper.categoryWhat)
                )

                call.enqueue(
                    onSuccess = {
                        Toast.makeText(this, "타임라인이 추가되었습니다.", Toast.LENGTH_SHORT).show()
                        var intent = Intent()
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    },
                    onFail = {status, message ->  toast(message)
                    }
                )
            }else{
                Toast.makeText(this, "모든 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }

        }

        deleteBtn()
    }


    /* 타임라인 추가할 때 선택해야 하는 카테고리 그리디 리사이클러뷰 세팅 */
    fun timelineCategoryRv(){
        rv_timeline_category = findViewById(R.id.rv_timelineadd)
        adapter_timeline_category = TimelineCategoryAdapter(this)
        rv_timeline_category.adapter = adapter_timeline_category
        rv_timeline_category.layoutManager = GridLayoutManager(this, 4)

        /* 카테고리 6개 배치 그리드 리사이클러뷰*/
        adapter_timeline_category.data = listOf(
            TimelineCategoryData(
                category = "인턴"
            ),
            TimelineCategoryData(
                category = "대외활동"
            ),
            TimelineCategoryData(
                category = "공모전"
            ),
            TimelineCategoryData(
                category = "동아리"
            ),
            TimelineCategoryData(
                category = "자격증"
            ),
            TimelineCategoryData(
                category = "기타"
            )
        )
        adapter_timeline_category.notifyDataSetChanged()
    }



    /* 엑스 버튼 세팅 */
    fun deleteBtn(){
        img_timelineadd_delete.setOnClickListener {
            /* 타임라인 추가 변수 초기화 */
            TimelineHelper.category = false
            TimelineHelper.isTitle = false
            DatePickderHelper.startDate = ""
            DatePickderHelper.endDate = ""
            TimelineHelper.isOK = false
            finish()
        }
    }

    /* 시작 날짜 달력 피커 */
    fun showStartDatePickerDialog(v : View) {
        val newFragment : DialogFragment = StartDatePicker()
        newFragment.show(supportFragmentManager, "startDatePicker")
    }

    /* 종료 날짜 달력 피커 */
    fun showEndDatePickerDialog(v: View) {
        val newFragment = EndDatePicker()
        newFragment.show(supportFragmentManager, "endDatePicker")

    }
        //adapter
     inner class TimelineCategoryAdapter(private val context: Context) :
            RecyclerView.Adapter<TimelineCategoryViewHolder>() {
            var data = listOf<TimelineCategoryData>()

            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): TimelineCategoryViewHolder {
                val view =
                    LayoutInflater.from(context).inflate(R.layout.rv_filterbox_item, parent, false)
                return TimelineCategoryViewHolder(view)
            }

            override fun onBindViewHolder(holder: TimelineCategoryViewHolder, position: Int) {
                holder.bind(data[position])

                holder.itemView.setOnClickListener {


                    val checkedTextView =
                        holder.itemView.findViewById<CheckedTextView>(R.id.checktxt_filter)

                    //내가 선택한 CheckedTextView의 정보 저장
                    if (TimelineHelper.count == 0) { //사용자가 선택한 필터가 없는 경우, current == null
                        TimelineHelper.currentButton = checkedTextView //CheckedTextView 정보 저장
                        TimelineHelper.count++ //count 증가
                        TimelineHelper.currentButton?.toggle() //drawable 변경
                        TimelineHelper.currentButton?.setTextColor(Color.parseColor("#ffc200")) //텍스트 색상 변경(YELLOW)
                        TimelineHelper.category = true
                    } else if (TimelineHelper.count == 1) { //사용자가 선택한 필터가 있는 경우, current != null
                        if (TimelineHelper.currentButton!!.equals(checkedTextView)) { //사용자가 선택한 필터가 현재 선택된 필터인 경우
                            TimelineHelper.count--
                            TimelineHelper.currentButton?.toggle() //drawable 변경
                            TimelineHelper.currentButton?.setTextColor(Color.parseColor("#000000")) //텍스트 색상 변경(BLACK)
                            TimelineHelper.currentButton = null //현재 선택된 버튼 초기화
                            TimelineHelper.category = false
                        } else { //사용자가 선택한 필터가 이전과 다른 경우 //현재와 다른 필터를 선택한 경우
                            TimelineHelper.currentButton?.toggle() //현재로 저장된 이전 필터 drawable 변경
                            TimelineHelper.currentButton?.setTextColor(Color.parseColor("#000000")) //텍스트 색상 변경(BLACK)
                            TimelineHelper.lastButton =
                                FilterHelper.currentButton //현재 필터를 이전 필터로 변경
                            //카운트는 동일하게 1로 유지
                            TimelineHelper.currentButton = checkedTextView
                            TimelineHelper.currentButton?.toggle() //drawable 변경
                            TimelineHelper.currentButton?.setTextColor(Color.parseColor("#ffc200")) //텍스트 색상 변경(YELLOW)
                            TimelineHelper.category = true
                        }
                    }

                    SelectHelper.categoryWhat = TimelineHelper.currentButton?.text.toString()

                    /** 모든 정보가 입력된 경우에만 등록버튼 활성화하기 위한 코드 */
                    if(TimelineHelper.isTitle != true && DatePickderHelper.startDate != "" && DatePickderHelper.endDate != "" && TimelineHelper.category != false){
                        txt_timelineadd_add.setTextColor(Color.parseColor("#ffc200"))
                        TimelineHelper.isOK = true
                    }else{
                        txt_timelineadd_add.setTextColor(Color.parseColor("#dbdbdb"))
                        TimelineHelper.isOK = false
                    }

                }
            }

            override fun getItemCount(): Int {
                return data.size
            }
        }

        //view holder
        inner class TimelineCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val category: CheckedTextView = itemView.findViewById(R.id.checktxt_filter)

            fun bind(data: TimelineCategoryData) {
                category.text = data.category
            }
        }

}
