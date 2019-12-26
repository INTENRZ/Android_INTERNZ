package com.example.internz.feature.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.data.signup.SignUpData
import com.example.internz.data.signup.SignUpRequestData
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * TODO! 이메일, 비밀번호, 핸드폰 번호를 서버로 전달해야 함
 * 유일값 : 이메일
 */

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
            val call = ApiServiceImpl.service.requestSignUp(
                SignUpRequestData(
                    edtSignUpEmail.text.toString(), edtSignUpPwd.text.toString(), edtSignUpPhoneNum.text.toString()
                )
            )

            call.enqueue(
                object : Callback<SignUpData> {
                    override fun onFailure(call: Call<SignUpData>, t: Throwable) {
                        Log.e("TAG", "SignUpActivity 서버 통신 불가")
                    }

                    override fun onResponse(
                        call: Call<SignUpData>,
                        response: Response<SignUpData>
                    ) {
                        if (response.isSuccessful) {
                            val intent = Intent(this@SignUpActivity, SignUp2Activity::class.java)
                            startActivity(intent)
                        }
                        else {
                            //TODO! 서버에서 전달해주는 메시지 확인
                            //TODO! 서버에서 해당 답장을 주는 경우가 이메일이 유일하지 않을 경우만이면 그냥 custom message로 수정
                        }
                    }
                }
            )
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
