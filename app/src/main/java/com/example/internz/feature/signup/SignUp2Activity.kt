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
import com.example.internz.data.signup2.SignUp2Data
import com.example.internz.data.signup2.SignUp2RequestData
import com.example.internz.feature.signin.SignInActivity
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
    private lateinit var userIndex : String
    private var gender : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up2)

        signUpFunction()
    }

    private fun signUpFunction() {
        //사용자의 고유 인덱스 초기화
        userIndex = intent.getStringExtra("userIndex")

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
            val call = ApiServiceImpl.service.requestSignUp2(
                userIndex,
                SignUp2RequestData(
                    edtSignUpName.text.toString(), edtSignUpNick.text.toString(), edtSignUpBirth.text.toString(), gender
                )
            )

            call.enqueue(
                object : Callback<SignUp2Data> {
                    override fun onFailure(call: Call<SignUp2Data>, t: Throwable) {
                        Log.e("TAG", "SignUp2Activity Server is not activated")
                    }

                    override fun onResponse(
                        call: Call<SignUp2Data>,
                        response: Response<SignUp2Data>
                    ) {
                        if (response.isSuccessful) {
                            if(response.body()?.success!!) {
                                val intent =
                                    Intent(this@SignUp2Activity, SignInActivity::class.java)
                                //이전의 모든 액티비티 제거
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                startActivity(intent)
                            } else {
                                Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT).show()

                                //닉네임 재입력 요청
                                edtSignUpNick.text.clear()
                                edtSignUpNick.requestFocus()
                            }
                        } else {
                            Log.e("TAG", "SignUp2Activiy Server broadcast fail")
                        }
                    }
                }
            )
        }
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
