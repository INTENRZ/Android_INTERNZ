package com.example.internz.feature.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.internz.R
import com.example.internz.api.ApiServiceImpl
import com.example.internz.data.SignUpData
import com.example.internz.data.SignUpRequestData
import com.example.internz.feature.signin.SignInActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUp2Activity : AppCompatActivity() {
    private lateinit var email : String
    private lateinit var password: String
    private lateinit var phoneNum : String
    private var gender : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up2)

        signUpFunction()
    }

    private fun signUpFunction() {
        initialVariable()

        //이름
        edtSignUpName.addTextChangedListener(
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
        edtSignUpNick.addTextChangedListener(
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
        edtSignUpBirth.addTextChangedListener(
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
            val radioButton = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            if (radioButton.text.toString().equals("남성")) {
                gender = 1
            }

            //서버통신구현
            val call = ApiServiceImpl.service.requestSignUp(
                SignUpRequestData(email, password, phoneNum, edtSignUpName.text.toString(),
                    edtSignUpNick.text.toString(), edtSignUpBirth.text.toString(), gender))

            call.enqueue(
                object : Callback<SignUpData> {
                    override fun onFailure(call: Call<SignUpData>, t: Throwable) {
                        Log.e("TAG", "SignUp2Activity 서버 통신 불가")
                    }

                    override fun onResponse(
                        call: Call<SignUpData>,
                        response: Response<SignUpData>
                    ) {
                        if (response.isSuccessful) {
                            //TODO! 로그인 화면으로 전환 (기획파트와 확인 필요)
                            val intent = Intent(this@SignUp2Activity, SignInActivity::class.java)
                            startActivity(intent)

                            //TODO! 뒤의 모든 액티비티 종료
                        } else {
                            Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            )
        }
    }

    private fun initialVariable() {
        email = intent.getStringExtra("email")
        password = intent.getStringExtra("pwd")
        phoneNum = intent.getStringExtra("phoneNum")
    }

    private fun changeBtnBackground() {
        if (edtSignUpName.text.isEmpty() || edtSignUpNick.text.isEmpty() || edtSignUpBirth.text.isEmpty()) {
            btnSignUpFinish.setBackgroundResource(R.drawable.btn_shape)
        } else {
            if(imgbtnSignUpService.isSelected && imgbtnSignUpMarketing.isSelected) {
                btnSignUpFinish.setBackgroundResource(R.drawable.btn_shape_ok)
            } else {
                Toast.makeText(applicationContext, "약관동의를 선택하세요.", Toast.LENGTH_SHORT).show()
                txtSignUpService.requestFocus()
            }
        }
    }
}
