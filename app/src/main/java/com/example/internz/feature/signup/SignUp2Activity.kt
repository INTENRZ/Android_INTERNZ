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
import com.example.internz.common.BaseResponse
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
import java.util.regex.Pattern

/**
 * TODO! 이름, 닉네임, 생년월일, 성별을 서버에 전달해야 함
 * 유일값 : 닉네임
 * TODO! 상단바 뒤로가기 이미지뷰 기능 추가
 */
class SignUp2Activity : AppCompatActivity() {
    private val nameP = Pattern.compile("^[가-힣]*\$", Pattern.CASE_INSENSITIVE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up2)

        signUp2Function()
    }

    private fun signUp2Function() {


        //성별(radio_group)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupSignUp)

        //서비스 이용 약관 동의(image_button)
        imgbtnSignUpService.setOnClickListener {
            when(imgbtnSignUpService.isSelected) {
                true -> {
                    imgbtnSignUpService.isSelected = false
                    btnSignUpFinish.setBackgroundResource(R.drawable.btn_shape)}
                false -> {
                    imgbtnSignUpService.isSelected = true
                    btnSignUpFinish.setBackgroundResource(R.drawable.btn_shape_ok)
                }
            }

        }

        //서비스 이용 약관 동의(TextView)
        txtSignUpService.setOnClickListener {
            when(txtSignUpService.isSelected) {
                true -> { txtSignUpService.isSelected = false
                    imgbtnSignUpService.isSelected = false
                    btnSignUpFinish.setBackgroundResource(R.drawable.btn_shape)}
                false -> { txtSignUpService.isSelected = true
                    imgbtnSignUpService.isSelected = true
                    btnSignUpFinish.setBackgroundResource(R.drawable.btn_shape_ok)}
            }
        }


        //(선택) 마케팅 정보 수신 동의
        imgbtnSignUpMarketing.setOnClickListener {
            when(imgbtnSignUpMarketing.isSelected) {
                true -> imgbtnSignUpMarketing.isSelected = false

                false -> imgbtnSignUpMarketing.isSelected = true

                }
            }


        //(선택) 마케팅 정보 수신 동의
        txtSignUpMarketing.setOnClickListener {
            when(txtSignUpMarketing.isSelected) {
                true ->  {
                    txtSignUpMarketing.isSelected = false
                    imgbtnSignUpMarketing.isSelected = false
                }

                false -> {
                    txtSignUpMarketing.isSelected = true
                    imgbtnSignUpMarketing.isSelected = true
                }
            }
        }

        //뒤로가기(back button)
        findViewById<ImageView>(R.id.imgSignUp2Back).setOnClickListener {
            this.finish()
        }

        btnSignUpFinish.setOnClickListener {


            if(!nameP.matcher(edtSignUp2Name.text.toString()).matches())
            {
                Toast.makeText(this, "올바른 이름을 입력해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(edtSignUp2Nick.length()<2)
            {
                Toast.makeText(this, "두 글자 이상의 닉네임을 입력해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(edtSignUp2Birth.length()<6)
            {
                Toast.makeText(this, "올바른 생년월일을 입력해 주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(!imgbtnSignUpService.isSelected)
            {
                Toast.makeText(this, "서비스 이용 약관에 동의하여 주십시오.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

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
