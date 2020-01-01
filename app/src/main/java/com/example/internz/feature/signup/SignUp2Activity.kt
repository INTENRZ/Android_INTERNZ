package com.example.internz.feature.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.api.ApiServiceImpl.getUserIdx
import com.example.internz.common.CallWithoutDataExt
import com.example.internz.common.enqueue
import com.example.internz.common.toast
import com.example.internz.data.signup.SignUpRequestData
import com.example.internz.data.signup2.SignUp2RequestData
import com.example.internz.feature.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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


        //성별(radio_group)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupSignUp)

        //서비스 이용 약관 동의(image_button)
        imgbtnSignUpService.setOnClickListener {
            when(imgbtnSignUpService.isSelected) {
                true -> imgbtnSignUpService.isSelected = false
                false -> imgbtnSignUpService.isSelected = true

            }
            btnSignUpFinish.setBackgroundResource(R.drawable.btn_shape_ok)
        }

        //서비스 이용 약관 동의(TextView)
        txtSignUpService.setOnClickListener {
            when(txtSignUpService.isSelected) {
                true -> txtSignUpService.isSelected = false
                false -> txtSignUpService.isSelected = true

            }
            btnSignUpFinish.setBackgroundResource(R.drawable.btn_shape_ok)
        }


        btnSignUpFinish.setOnClickListener {

            val intent = getIntent()
            val name : String = edtSignUp2Name.text.toString()
            val nickname : String = edtSignUp2Nick.text.toString()
            val email : String = intent.getStringExtra("email")
            val password : String = intent.getStringExtra("password")
            val phone : String = intent.getStringExtra("phone")
            val age : String = edtSignUp2Birth.text.toString()
            var sex : String ="0"

            val radioButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            if (radioButton.text.toString().equals("남성")) {
                sex = "1"
            }

            val signUp2Call = ApiServiceImpl.service.requestSignUp2(
                SignUp2RequestData(
                    email,password,phone,name,nickname,age,sex

                )
            )

            signUp2Call.enqueue(

                onSuccess = {
                    val intent = Intent(applicationContext, SignInActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)

                },
                onFail = {
                        status, message -> toast(message)
                }
            )

        }
    }


}
