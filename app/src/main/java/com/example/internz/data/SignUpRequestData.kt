package com.example.internz.data

//TODO! 뷰 추가하는대로 데이터 추가 필요함
class SignUpRequestData (
    val email : String,
    val pwd : String,
    val phoneNum : String,
    val name : String,
    val nickname : String,
    val birth : String,
    val gender : Int //여자 : 0, 남자 : 1
)