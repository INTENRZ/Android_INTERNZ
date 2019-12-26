package com.example.internz.feature.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.internz.R
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up2.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signUpFunction();
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
        edtSignUpPhoneNum?.addTextChangedListener(
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
            val intent = Intent(this@SignUpActivity, SignUp2Activity::class.java)
                .putExtra("email", edtSignUpEmail.text.toString())
                .putExtra("pwd", edtSignUpPwd.text.toString())
                .putExtra("phoneNum", edtSignUpPhoneNum.text.toString())

            startActivity(intent)
        }
    }

    private fun changeBtnBackground() {
        if (edtSignUpEmail.text.isEmpty() || edtSignUpPhoneNum.text.isEmpty() || edtSignUpPwd.text.isEmpty() || edtSignUpPwdChk.text.isEmpty()) {
            btnSignUpNext.setBackgroundResource(R.drawable.btn_shape)
        } else {
            btnSignUpNext.setBackgroundResource(R.drawable.btn_shape_ok)
        }
    }
}
