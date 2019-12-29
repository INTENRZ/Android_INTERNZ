package com.example.internz.feature.setprofile

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.internz.R
import com.example.internz.common.toast
import com.example.internz.feature.jobselect.SelectHelper
import com.example.internz.ui.MainActivity
import kotlinx.android.synthetic.main.activity_set_profile.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class SetProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_profile)


        setProfileActivity()
    }

    private fun setProfileActivity() {
        imgSetProfile.makeCircle()

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
            startActivity(Intent(this, MainActivity::class.java))
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
            Log.d("seungmin", "${data?.data}")
            //Glide를 이용한 make image oval 실패
            Glide
                .with(this)
                .load(data?.data)
                .apply(RequestOptions.circleCropTransform())
                .into(imgSetProfile);

            //TODO! dhodenhof를 이용한 원형 이미지 생성 -> 실패, Uri convert to Url?
//            imgSetProfile.setCircleBackgroundColorResource(data?.data)
        }
    }

    private fun ImageView.makeCircle() {
        Glide
            .with(this@SetProfileActivity)
            .load(this.drawable)
            .apply(RequestOptions.circleCropTransform())
            .into(this)
    }
}
