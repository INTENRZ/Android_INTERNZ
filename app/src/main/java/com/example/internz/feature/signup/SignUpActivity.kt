package com.example.internz.feature.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ImageView
import com.example.internz.R
import com.example.internz.common.enqueue
import com.example.internz.common.toast
import com.example.internz.data.signup.SignUpRequestData
import com.example.internz.feature.jobselect.JobSelectActivity
import com.example.internz.ui.BottomBarActivity
import com.example.internz.api.ApiServiceImpl
import com.example.internz.api.ApiServiceImpl.setUserIdx
import kotlinx.android.synthetic.main.activity_sign_up.*

/**
 * TODO! 상단바 뒤로가기 이미지뷰 기능 추가
 */

class SignUpActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signUpFunction()
        changeBtnBackground()
    }

    private fun signUpFunction() {
        //이메일
        edtSignUpEmail?.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    changeBtnBackground()
                }
            }
        )

        //이름
        edtSignUpPhone?.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    changeBtnBackground()
                }
            }
        )

        //비밀번호
        edtSignUpPwd?.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    changeBtnBackground()
                }
            }
        )

        //비밀번호 확인
        edtSignUpPwdChk?.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    changeBtnBackground()
                }
            }
        )

        //로그인하기(click_event)
        txtSignUpLogIn?.setOnClickListener{
            this.finish()
        }

        //다음(click_event)
        //TODO! 모든 항목이 입력되어야 다음으로 넘어가도록 수정
        btnSignUpNext?.setOnClickListener {
            val email = edtSignUpEmail.text.toString()
            val signUpCall = ApiServiceImpl.service.requestSignUp(
                SignUpRequestData(
                    email
                )
            )

            signUpCall.enqueue(

                onSuccess = {
                    val intent = Intent(applicationContext, SignUp2Activity::class.java)
                    startActivity(intent)
                },

                onFail = {status, message ->  toast(message)
                }
            )
        }



        //뒤로가기(back button)
        findViewById<ImageView>(R.id.imgSignUpBack).setOnClickListener {
            this.finish()
        }
    }

    private fun changeBtnBackground() {
        if (edtSignUpEmail.text.isEmpty() || edtSignUpPhone.text.isEmpty() || (edtSignUpPwd.text.length < 6) || (edtSignUpPwdChk.text.length < 6)) {
            btnSignUpNext.setBackgroundResource(R.drawable.btn_shape)
        } else {
            btnSignUpNext.setBackgroundResource(R.drawable.btn_shape_ok)
        }
    }
}
