package com.example.internz.data.signup

//클라이언트 -> 서버
data class SignUpRequestData(
    val email : String,
    val pwd : String,
    val phoneNum : String
)