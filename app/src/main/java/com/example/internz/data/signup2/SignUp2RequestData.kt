package com.example.internz.data.signup2

import com.google.gson.annotations.SerializedName

//TODO! 뷰 추가하는대로 데이터 추가 필요함
class SignUp2RequestData (
    val email : String,
    val password : String,
    val phone : String,
    val name : String, //여자 : 0, 남자 : 1
    val nickname : String,
    val age : String,
    val sex : String //여자 : 0, 남자 : 1
)