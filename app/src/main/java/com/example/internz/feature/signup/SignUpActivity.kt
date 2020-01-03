package com.example.internz.feature.signup

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.internz.R
import com.example.internz.common.enqueue
import com.example.internz.common.toast
import com.example.internz.data.signup.SignUpRequestData
import com.example.internz.feature.jobselect.JobSelectActivity
import com.example.internz.ui.BottomBarActivity
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.CallWithoutDataExt
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.regex.Pattern
import retrofit2.Call

/**
 * TODO! 상단바 뒤로가기 이미지뷰 기능 추가
 */

class SignUpActivity : AppCompatActivity() {
    private val pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signUpFunction()
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

            //이메일 형식 검사
            if (!pattern.matcher(edtSignUpEmail.text.toString()).matches()) {
                Toast.makeText(this, "올바른 이메일 형식을 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if( edtSignUpPwd.length() < 6)
            {
                Toast.makeText(this, "비밀번호가 형식에 맞지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(edtSignUpPwd.text.toString() != edtSignUpPwdChk.text.toString())
            {
                Toast.makeText(this, "비밀번호 확인이 맞지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(edtSignUpPhone.length() != 11 )
            {
                Toast.makeText(this, "올바른 휴대폰 번호를 입력해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val email = edtSignUpEmail.text.toString()
            val password = edtSignUpPwd.text.toString()
            val phone = edtSignUpPhone.text.toString()
            val signUpCall : Call<CallWithoutDataExt> = ApiServiceImpl.service.requestSignUp(
                SignUpRequestData(email)
            )

            signUpCall.enqueue(

                onSuccess = {
                    val intent = Intent(applicationContext, SignUp2Activity::class.java)
                    intent.putExtra("email", email)
                    intent.putExtra("password", password)
                    intent.putExtra("phone" , phone)
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
