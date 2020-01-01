package com.example.internz.feature.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.CallWithoutDataExt
import com.example.internz.common.enqueue
import com.example.internz.common.toast
import com.example.internz.data.signup2.SignUp2RequestData
import com.example.internz.feature.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_sign_up2.*
import retrofit2.Call

/**
 * TODO! 이름, 닉네임, 생년월일, 성별을 서버에 전달해야 함
 * 유일값 : 닉네임
 * TODO! 상단바 뒤로가기 이미지뷰 기능 추가
 */
class SignUp2Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up2)

        signUpFunction()
    }

    private fun signUpFunction() {
        //사용자의 고유 인덱스 초기화
//        userIndex = intent.getStringExtra("userIndex")

        //이름
        edtSignUp2Name.addTextChangedListener(
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

        //닉네임
        edtSignUp2Nick.addTextChangedListener(
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

        //생년월일
        edtSignUp2Birth.addTextChangedListener(
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

        //성별(radio_group)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupSignUp)

        //서비스 이용 약관 동의(image_button)
        imgbtnSignUpService.setOnClickListener {
            when(imgbtnSignUpService.isSelected) {
                true -> imgbtnSignUpService.isSelected = false
                false -> imgbtnSignUpService.isSelected = true
            }
        }

        //마케팅 정보 수신 동의(image_button)
        imgbtnSignUpMarketing.setOnClickListener {
            when(imgbtnSignUpMarketing.isSelected) {
                true -> imgbtnSignUpMarketing.isSelected = false
                false -> imgbtnSignUpMarketing.isSelected = true
            }
        }

        //서비스 이용 약관 동의(TextView)
        txtSignUpService.setOnClickListener {
            when(imgbtnSignUpService.isSelected) {
                true -> imgbtnSignUpService.isSelected = false
                false -> imgbtnSignUpService.isSelected = true
            }
        }

        //마케팅 정보 수신 동의(TextView)
        txtSignUpMarketing.setOnClickListener {
            when(imgbtnSignUpMarketing.isSelected) {
                true -> imgbtnSignUpMarketing.isSelected = false
                false -> imgbtnSignUpMarketing.isSelected = true
            }
        }

        btnSignUpFinish.setOnClickListener {
            //선택된 라디오버튼 정보(성별)

            Log.e("dddd", "성공1")
            val intent = getIntent()
            Log.e("dddd", "성공2")
            val name : String = edtSignUp2Name.text.toString()
            val nickname : String = edtSignUp2Nick.text.toString()
            val email : String = intent.getStringExtra("email")
            val password : String = intent.getStringExtra("password")
            val phone : String = intent.getStringExtra("phone")
            val age : String = edtSignUp2Birth.text.toString()
            var sex : String ="0"

            Log.e("dddd", "성공3")

            val radioButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)

            if (radioButton.text.toString().equals("남성")) {
                sex = "1"
            }

            Log.e("dddd", "${name}, ${nickname}, ${email}, ${password}, ${phone}, ${age}, ${sex}")

            Log.e("dddd", "성공4")
            val signUp2Call: Call<CallWithoutDataExt> = ApiServiceImpl.service.requestSignUp2(
                SignUp2RequestData(
                    email,password,phone,name,nickname,age,sex

                )
            )

            Log.e("dddd", "성공5")
            signUp2Call.enqueue(
                onSuccess = {
                    Log.e("aaaa", "zzzzzzzzz")
                    val intent = Intent(applicationContext, SignInActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)

                },

                onFail = { status, message -> toast("fail입니다")
                }
            )

        }
    }

    private fun changeBtnBackground() {
        if (edtSignUp2Name.text.isEmpty() || edtSignUp2Nick.text.isEmpty() || (edtSignUp2Birth.text.length != 6)) {
            btnSignUpFinish.setBackgroundResource(R.drawable.btn_shape)
        } else {
            if(imgbtnSignUpService.isSelected) {
                btnSignUpFinish.setBackgroundResource(R.drawable.btn_shape_ok)
            } else {
                Toast.makeText(applicationContext, "서비스 이용 약관동의를 선택하세요.", Toast.LENGTH_SHORT).show()
                txtSignUpService.requestFocus()
            }
        }
    }
}
