package com.example.internz.feature.signin

import android.app.ActionBar
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Layout
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginBottom
import androidx.core.view.updatePadding
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.data.signin.SignInData
import com.example.internz.data.signin.SignInRequestData
import com.example.internz.feature.HomeActivity
import com.example.internz.feature.signup.SignUpActivity
import com.example.internz.feature.story.StoryActivity
import kotlinx.android.synthetic.main.activity_sign_in.*
import retrofit2.Call
import retrofit2.Response

/**
 * TODO! 키보드 올라올때 뷰 위로 올리기 수정
 */

class SignInActivity : AppCompatActivity() {
    private var backKeyPressedTime : Long = 0
    //다중 액티비티 종료

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        signInFunction();
    }

    private fun signInFunction() {
        //이메일 TextWatcher
        edtSignInEmail?.addTextChangedListener(
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
        edtSignInPwd?.addTextChangedListener(
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
        btnSignInLogIn?.setOnClickListener {
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
            val signInCall = ApiServiceImpl.service.requestSignIn(
                SignInRequestData(
                    email,
                    pwd
                )
            )

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
                            val intent = Intent(applicationContext, HomeActivity::class.java)
                            startActivity(intent)
                        }
                        else {
                            //TODO! 서버->클라이언트 메시지가 정상적으로 출력되지 않음
                            Log.e("TAG", response.body()?.message.toString())
                        }
                    }
                }
            )
        }


        //회원가입
        txtSignInSignUp?.setOnClickListener{
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

        //레이아웃 변화 감지
        constSignIn.addOnLayoutChangeListener(
            object : View.OnLayoutChangeListener {
                override fun onLayoutChange(
                    v: View?,
                    left: Int,
                    top: Int,
                    right: Int,
                    bottom: Int,
                    oldLeft: Int,
                    oldTop: Int,
                    oldRight: Int,
                    oldBottom: Int
                ) {
                    if (bottom < oldBottom) {
                        //TODO! marginBottom 마진 줄이기
                    } else {
                        //TODO! 기본 마진으로 변화
                    }
                }
            }
        )

        //스토리액티비티 이동
        btnMoveStory.setOnClickListener {
            startActivity(Intent(this, StoryActivity::class.java))
            finish()
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
