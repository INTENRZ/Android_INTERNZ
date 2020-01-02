package com.example.internz.feature.message.messagesend

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import com.example.internz.data.message.MessageSendRequestData
import kotlinx.android.synthetic.main.activity_message_send.*

class MessageSendActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message_send)

        messageSendFunction()
    }

    private fun messageSendFunction() {
        //전송
        txtSendSend.setOnClickListener {
            //입력된 메시지가 있을 경우 전송
            if(edtSendMessage.text.isNotEmpty()) {
                sendMessage()
            }
            else {
                Toast.makeText(this, "메시지를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        //취소
        imgSendBack.setOnClickListener {
            finish()
        }

        edtSendMessage.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(edt: Editable?) {
                    //작성되면 color change
                    if (edt?.isNotEmpty()!!) {
                        txtSendSend.setTextColor(Color.parseColor("#ffc200"))
                    } else {
                        txtSendSend.setTextColor(Color.parseColor("#dbdbdb"))
                    }
                }
            }
        )
    }

    private fun sendMessage() {
        val call = ApiServiceImpl.service.requestSendMessage(
            ApiServiceImpl.getToken(),
            MessageSendRequestData(ApiServiceImpl.getReceiverIdx().toInt(), edtSendMessage.text.toString())
        )

        call.enqueue(
            onSuccess = {
                if(it.success.equals("true")) {
                    Toast.makeText(this, "전송 완료", Toast.LENGTH_SHORT).show()
                    setResult(Activity.RESULT_OK) //TODO! list activity로 돌아가서 새로고침하기 구현

                    finish()
                } else {
                    Toast.makeText(this, "재전송이 필요합니다.", Toast.LENGTH_SHORT).show()
                }
            },
            onFail = {
                status, message -> Log.e("TAG", "메시지 전송 FAIL : ${status}, ${message}")
            }
        )
    }
}
