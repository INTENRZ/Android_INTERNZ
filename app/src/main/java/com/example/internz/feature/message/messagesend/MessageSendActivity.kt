package com.example.internz.feature.message.messagesend

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.internz.R
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
}
