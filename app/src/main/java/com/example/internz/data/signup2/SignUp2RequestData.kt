package com.example.internz.data.signup2

import com.google.gson.annotations.SerializedName

//TODO! 뷰 추가하는대로 데이터 추가 필요함
class SignUp2RequestData (
    @SerializedName("name")
    val name : String,
    @SerializedName("nickname")
    val nickname : String,
    @SerializedName("age")
    val birth : String,
    @SerializedName("password")
    val password : String,
    @SerializedName("phone")
    val phone : String,
    @SerializedName("sex")
    val gender : Int //여자 : 0, 남자 : 1
)