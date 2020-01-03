package com.example.internz.feature.setprofile

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.internz.R
import com.example.internz.api.ApiService
import com.example.internz.api.ApiServiceImpl
import com.example.internz.common.enqueue
import com.example.internz.common.toast
import com.example.internz.data.firstsignin.FirstSignInRequestData
import com.example.internz.feature.SelectHelper
import com.example.internz.ui.BottomBarActivity
import kotlinx.android.synthetic.main.activity_set_profile.*

class SetProfileActivity : AppCompatActivity() {
    private var imagePath : String = ""
    private lateinit var activity : Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_profile)

        broadcastForNickname()
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
                val call = ApiServiceImpl.service.requestFirstSignIn(
                    ApiServiceImpl.getToken(),
                    FirstSignInRequestData(
                        SelectHelper.arrayList[0],
                        SelectHelper.arrayList[1],
                        SelectHelper.arrayList[2],
                        imagePath,
                        edtSetProfileContents.text.toString()
                    )
                )

                Log.e("TAG", "${SelectHelper.arrayList[0]},${SelectHelper.arrayList[1]},${SelectHelper.arrayList[2]},${imagePath},${edtSetProfileContents.text.toString()}")

                call.enqueue(
                    onSuccess = {
                        val intent = Intent(this@SetProfileActivity, BottomBarActivity::class.java)
                        startActivity(intent)

                        ActivityCompat.finishAffinity(activity)
                    },
                    onFail = {
                        status, message -> toast(message)
                    }
                )
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
            Log.e("TAG", "${imagePath}")
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

    private fun broadcastForNickname() {
        val call = ApiServiceImpl.service.requestNickname(
            ApiServiceImpl.getToken()
        )

        Log.e("TAG", "${ApiServiceImpl.getToken()}")

        call.enqueue(
            onSuccess = {
                textView14.text = it.nickname
            },
            onFail = {
                status, message -> Log.e("TAG", "SetProfileActivity : 닉네임 받아오는 통신 FAIL ${status}, ${message}")
            }
        )
    }
}
