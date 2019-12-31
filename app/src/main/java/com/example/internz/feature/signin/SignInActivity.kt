package com.example.internz.feature.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.api.ApiServiceImpl.setToken
import com.example.internz.common.BaseResponse
import com.example.internz.common.enqueue
import com.example.internz.common.toast
import com.example.internz.data.signin.SignInData
import com.example.internz.data.signin.SignInRequestData
import com.example.internz.feature.jobselect.JobSelectActivity
import com.example.internz.feature.signup.SignUpActivity
import com.example.internz.ui.BottomBarActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import retrofit2.Call


class SignInActivity : AppCompatActivity() {

    private var backKeyPressedTime: Long = 0
    //다중 액티비티 종료

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInFunction()
    }

    private fun signInFunction() {
        //이메일 TextWatcher
        edtSignInEmail?.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    if (edtSignInEmail.text.isNotEmpty() && edtSignInPwd.text.isNotEmpty()) {
                        btnSignInLogIn.setBackgroundResource(R.drawable.btn_shape_ok)
                    } else {
                        btnSignInLogIn.setBackgroundResource(R.drawable.btn_shape)
                    }
                }
            }
        )

        //비밀번호 TextWatcher
        edtSignInPwd?.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    if (edtSignInEmail.text.isNotEmpty() && edtSignInPwd.text.isNotEmpty()) {
                        btnSignInLogIn.setBackgroundResource(R.drawable.btn_shape_ok)
                    } else {
                        btnSignInLogIn.setBackgroundResource(R.drawable.btn_shape)
                    }
                }
            }
        )

        //로그인(click_event)
        btnSignInLogIn?.setOnClickListener {
            val email = edtSignInEmail.text.toString()
            val pwd = edtSignInPwd.text.toString()

            //로그인 요청 to Server 불가
            if (email.isEmpty()) {
                toast("이메일을 입력하세요.")
                edtSignInEmail.requestFocus()
                return@setOnClickListener
            } else if (pwd.isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
                edtSignInPwd.requestFocus()
                return@setOnClickListener
            }

            //TODO! 파트장님이 extension 이용해서 서버 통신 구현하신 부분
            //로그인 요청
            val signIncall: Call<BaseResponse<SignInData>> = ApiServiceImpl.service.requestSignIn(
                SignInRequestData(email,pwd)
            )

            signIncall.enqueue(
               onSuccess = {
                   when {
                       it.isFirst == "0" -> {
                           setToken(it.token)
                           val intent = Intent(applicationContext, JobSelectActivity::class.java)
                           startActivity(intent)

                       }
                       it.isFirst == "1" -> {
                           setToken(it.token)
                           val intent = Intent(applicationContext, BottomBarActivity::class.java)
                           startActivity(intent)
                           finish()
                       }
                   }
               },
                onFail = {status, message ->  toast(message)
                }
            )
        }

        //회원가입
        txtSignInSignUp?.setOnClickListener {
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)


        }
    }

    //뒤로가기 2번 종료
    override fun onBackPressed() {
        val toast = Toast.makeText(this, "\'뒤로\'버튼을 한 번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT)

        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            toast.show()
        } else if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            toast.cancel()
            finish()
        }
    }
}

