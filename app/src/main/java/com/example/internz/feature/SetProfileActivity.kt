package com.example.internz.feature

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.internz.R
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import kotlinx.android.synthetic.main.activity_set_profile.*
import java.util.ArrayList
import java.util.jar.Manifest

class SetProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_profile)

        setProfileActivity()
    }

    private fun setProfileActivity() {
        //TODO! imgSetProfile glide로 이미지 round로 변경

        imgSetProfile.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_DENIED) {
                    //권한 거부
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    //권한 요청 팝업 생성
                    requestPermissions(permissions, PERMISSION_CODE)
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
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                    //권한 요청 팝업 띄우기
                    getImageFromGallery()
                }
                else {
                    //권한 거부
                    Toast.makeText(applicationContext, R.string.permission_1, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imgSetProfile.setImageURI(data?.data)
        }
    }
}
