package com.example.internz.feature.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.example.internz.R
import com.example.internz.api.SignInServiceImpl
import com.example.internz.data.SignInData
import com.example.internz.data.SignInRequestData
import com.example.internz.feature.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

/**
 * TODO! 키보드 올라올때 뷰 위로 올리기
 */

class SignInActivity : AppCompatActivity() {
    private var backKeyPressedTime : Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInFunction();
    }

    private fun signInFunction() {
        //이메일 TextWatcher
        edtSignInEmail.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    if(edtSignInEmail.text.isNotEmpty() && edtSignInPwd.text.isNotEmpty()) {
                        btnSignInLogIn.setBackgroundResource(R.drawable.btn_shape_ok)
                    } else {
                        btnSignInLogIn.setBackgroundResource(R.drawable.btn_shape)
                    }
                }
            }
        )

        //비밀번호 TextWatcher
        edtSignInPwd.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    if(edtSignInEmail.text.isNotEmpty() && edtSignInPwd.text.isNotEmpty()) {
                        btnSignInLogIn.setBackgroundResource(R.drawable.btn_shape_ok)
                    } else {
                        btnSignInLogIn.setBackgroundResource(R.drawable.btn_shape)
                    }
                }
            }
        )

        //로그인(click_event)
        btnSignInLogIn.setOnClickListener {
            val email = edtSignInEmail.text.toString()
            val pwd = edtSignInPwd.text.toString()

            //로그인 요청 불가
            if(email.isEmpty()) {
                Toast.makeText(this, "이메일을 입력하세요.", Toast.LENGTH_SHORT).show()
                edtSignInEmail.requestFocus()
                return@setOnClickListener
            }
            else if(pwd.isEmpty()) {
                Toast.makeText(this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
                edtSignInPwd.requestFocus()
                return@setOnClickListener
            }

            //로그인 요청
            val signInCall = SignInServiceImpl.singInService.requestSignIn(SignInRequestData(email, pwd))

            signInCall.enqueue(
                object : retrofit2.Callback<SignInData> {
                    override fun onFailure(call: Call<SignInData>, t: Throwable) {
                        Log.e("TAG", "SignInActivity 서버 통신 불가")
                    }

                    override fun onResponse(
                        call: Call<SignInData>,
                        response: Response<SignInData>
                    ) {
                        //로그인 성공
                        if (response.isSuccessful) {
                            //TODO! 직무 선택 페이지로 넘어가기
                        }
                        else {
                            //TODO! 오류 확인
                            Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
        }

        //회원가입
        txtSignInSignUp.setOnClickListener{
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
        }
        else if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            toast.cancel()
            finish()
        }
    }
}
