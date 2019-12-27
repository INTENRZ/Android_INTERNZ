package com.example.internz.feature.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.data.signin.SignInRequestData
import com.example.internz.ui.MainActivity
import com.example.internz.feature.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_sign_in.*

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

//            signInCall.enqueue(
//                object : retrofit2.Callback<SignInData> {
//                    override fun onFailure(call: Call<SignInData>, t: Throwable) {
//                        Log.e("TAG", "SignInActivity Server is not activated")
//                    }
//
//                    override fun onResponse(
//                        call: Call<SignInData>,
//                        response: Response<SignInData>
//                    ) {
//                        //서버 통신 성공
//                        if (response.isSuccessful) {
//                            if(response.body()?.isFirst.equals("0")) { //첫 로그인
//                                //사용자 토큰 저장
//                                SignIn.setUserToken(response.body()?.token!!) //TODO! 오류확인
//
//                                val intent = Intent(applicationContext, JobSelectActivity::class.java)
//                                startActivity(intent)
//                                finish()
//                            } else if (response.body()?.isFirst.equals("1")) { //old user
//                                //사용자 토큰 저장
//                                SignIn.setUserToken(response.body()?.token!!) //TODO! 오류 확인 -> 오류 발생
//
//                                val intent = Intent(applicationContext, HomeActivity::class.java)
//                                startActivity(intent)
//                                finish()
//                            } else {
//                                //로그인 불가
//                                if((response.body()?.message!!).contains("비밀번호")) {
//                                    Toast.makeText(applicationContext, response.body()?.message.toString(), Toast.LENGTH_SHORT).show()
//
//                                    //비밀번호 재입력 요청
//                                    edtSignInPwd.text.clear()
//                                    edtSignInPwd.requestFocus()
//                                } else {
//                                    Toast.makeText(applicationContext, response.body()?.message.toString(), Toast.LENGTH_SHORT).show()
//
//                                    edtSignInEmail.text.clear()
//                                    edtSignInPwd.text.clear()
//                                }
//                            }
//                        }
//                        else {
//                            Log.e("TAG", "SignInActivity Server broadcast fail")
//                        }
//                    }
//                }
//            )

            startActivity(Intent(this, MainActivity::class.java))
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
