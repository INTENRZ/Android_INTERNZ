package com.example.internz.data.firstsignin

//클라이언트 -> 서버
data class FirstSignInRequestData (
    val task1 : String,
    val task2 : String,
    val task3 : String,
    val image : String,
    val introduce : String
)