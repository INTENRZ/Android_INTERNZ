package com.example.internz.feature.setprofile

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.internz.R
import com.example.internz.common.toast
import kotlinx.android.synthetic.main.activity_set_profile.*

class SetProfileActivity : AppCompatActivity() {
    private var imagePath : String = ""
    private lateinit var activity : Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_profile)

        setProfileActivity()
    }

    private fun setProfileActivity() {
        imgSetProfile.makeCircle()
        activity = this

        //기본 이미지 click listener 지정
        imgSetProfile.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED) {
                    //권한 거부
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    //권한 요청 팝업 생성
                    requestPermissions(permissions,
                        PERMISSION_CODE
                    )
                }
                else {
                    //이미 권한이 생성된 경우
                    getImageFromGallery()
                }
            }
            else {
                //운영체제가 Version.M 이하인 경우
                getImageFromGallery()
            }
        }

        //카메라 이미지 click listener 지정
        imgbtnSetProfile.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED) {
                    //권한 거부
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    //권한 요청 팝업 생성
                    requestPermissions(permissions,
                        PERMISSION_CODE
                    )
                }
                else {
                    //이미 권한이 생성된 경우
                    getImageFromGallery()
                }
            }
            else {
                //운영체제가 Version.M 이하인 경우
                getImageFromGallery()
            }
        }

        //한줄 프로필 소개
        edtSetProfileContents.addTextChangedListener(
            object : TextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                //글자 개수 20 ~ 40
                override fun afterTextChanged(text: Editable?) {
                    if (text != null && !text.toString().equals("")) {
                        if(text.toString().length >= 20) {
                            btnSetProfileStart.setBackgroundResource(R.drawable.btn_shape_ok)
                        } else {
                            btnSetProfileStart.setBackgroundResource(R.drawable.btn_shape)
                        }
                    }
                }
            }
        )

        //시작하기 click listener
        btnSetProfileStart.setOnClickListener {
            if (edtSetProfileContents.text.length < 20) {
                edtSetProfileContents.requestFocus()
                Toast.makeText(applicationContext, "20자 이상의 한줄 소개를 작성해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                /*
                //TODO! 서버 통신
                val call = ApiServiceImpl.service.requestSettingAtFirstSignIn(
                    ApiServiceImpl.getToken(),
                    FirstSignInRequestData(
                        SelectHelper.arrayList.get(0),
                        SelectHelper.arrayList.get(1),
                        SelectHelper.arrayList.get(2),
                        imagePath,
                        edtSetProfileContents.text.toString()
                    )
                )

                call.enqueue(
                    //통신 성공
                    onSuccess = {
                        when(it.status) {
                            "200" -> {
                                //입력 완료 후 BottomBarActivity 이동
                                val intent = Intent(this@SetProfileActivity, BottomBarActivity::class.java)
                                startActivity(intent)

                                ActivityCompat.finishAffinity(activity)
                                Log.e("TAG", "status는 200입니다.")
                            }
                            "100" -> {
                                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                                Log.e("TAG", "status는 100입니다.")
                            }
                            "110" -> {
                                Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                                Log.e("TAG", "status는 110입니다.")
                            }
                        }
                    },
                    //통신 실패
                    onFail = {
                        status, message -> toast(message)
                        Log.e("TAG", "통신 실패")
                    }
                )
                 */


//                val call = ApiServiceImpl.service.requestSettingInFistSignIn(
//                    ApiServiceImpl.getToken(),
//                    FirstSignInRequestData(
//                        SelectHelper.arrayList.get(0),
//                        SelectHelper.arrayList.get(1),
//                        SelectHelper.arrayList.get(2),
//                        imagePath,
//                        edtSetProfileContents.text.toString()
//                    )
//                )
//
//                call.enqueue(
//                    object : Callback<FirstSignInResponseData> {
//                        override fun onFailure(call: Call<FirstSignInResponseData>, t: Throwable) {
//                            Log.e("TAG", "FirstSignInResponseData is not activated")
//                        }
//
//                        override fun onResponse(
//                            call: Call<FirstSignInResponseData>,
//                            response: Response<FirstSignInResponseData>
//                        ) {
//                            if (response.isSuccessful) {
//                                if(response.body()?.status.equals("200")) {
//                                    val intent = Intent(this@SetProfileActivity, BottomBarActivity::class.java)
//                                    startActivity(intent)
//
//                                    ActivityCompat.finishAffinity(activity)
//                                }
//                                else if (response.body()?.status.equals("100")) {
//                                    Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT).show()
//                                }
//                                else if (response.body()?.status.equals("110")) {
//                                    Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT).show()
//                                }
//                                else {
//                                    //...
//                                }
//                            }
//                            else {
//                                Log.e("TAG", "FirstSignInResponseData broadcast fail")
//                            }
//                        }
//                    }
//                )
            }
        }
    }

    private fun getImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //이미지 선택 코드
        private val IMAGE_PICK_CODE = 1000
        //권한 코드
        private val PERMISSION_CODE = 1001
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode) {
            PERMISSION_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    //권한 요청 팝업 띄우기
                    getImageFromGallery()
                }
                else {
                    //권한 거부
                    toast(R.string.permission_1)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            //원형 이미지 띄우기
            Glide
                .with(this)
                .load(data?.data)
                .apply(RequestOptions.circleCropTransform())
                .into(imgSetProfile)

            imagePath = data?.data.toString()
        }
    }

    //기본 이미지 원형 지정
    private fun ImageView.makeCircle() {
        Glide
            .with(this@SetProfileActivity)
            .load(this.drawable)
            .apply(RequestOptions.circleCropTransform())
            .into(this)
    }
}
