package com.example.internz.ui.story

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import com.example.internz.data.story.StoryAddRequestData
import com.example.internz.ui.profile.timeline.TimelineStoryHelper
import kotlinx.android.synthetic.main.activity_story_add.*

class StoryAddActivity : AppCompatActivity() {

    private lateinit var storyTitle : String
    private lateinit var storyContents : String
    var isTitle : Boolean = true
    var isContent : Boolean = true
    var addOK : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_add)

        /* 스토리 제목 입력 리스너 */
        et_storyAdd_title.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                storyTitle = et_storyAdd_title.text.toString()  // 스토리 제목
                isTitle = storyTitle.isEmpty()
                /* 제목과 내용이 비어있는데 등록 버튼을 눌렀을 경우 */
                if(isTitle == false && isContent == false){
                    txt_storyAdd_add.setTextColor(Color.parseColor("#ffc200"))
                    addOK = true
                }else{
                    addOK = false
                    txt_storyAdd_add.setTextColor(Color.parseColor("#dbdbdb"))
                }
            }
        })

        /* 스토리 내용 입력 리스너 */
        et_storyAdd_contents.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                storyContents = et_storyAdd_contents.text.toString() // 스토리 내용
                isContent = storyContents.isEmpty()
                /* 제목과 내용이 비어있는데 등록 버튼을 눌렀을 경우 */
                if(isTitle == false && isContent == false){
                    txt_storyAdd_add.setTextColor(Color.parseColor("#ffc200"))
                    addOK = true
                }else{
                    addOK = false
                    txt_storyAdd_add.setTextColor(Color.parseColor("#dbdbdb"))
                }
            }
        })

        addBtn()
        deleteBtn()

    }


    /** 등록버튼 클릭시 스토리 추가 서버 통신 요청 */
    fun addBtn(){
        txt_storyAdd_add.setOnClickListener {
            if(addOK == true){
                val call = ApiServiceImpl.service.requestStoryAdd(ApiServiceImpl.getToken(), TimelineStoryHelper.timelineIdx, StoryAddRequestData(
                    storyTitle,
                    storyContents
                ))
                call.enqueue(
                    onSuccess = {
                        Toast.makeText(this, "스토리가 등록되었습니다.", Toast.LENGTH_SHORT).show()
                        setResult(Activity.RESULT_OK)
                        finish()
                    },
                    onFail = { status, message ->
                    }
                )
            }else{
                Toast.makeText(this, "제목과 내용을 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun deleteBtn(){
        img_storyAdd_delete.setOnClickListener {
            finish()
        }
    }
}


