package com.example.internz.data.signin

import android.content.Context
import java.util.*

//Appjam 이후 develop을 위함

object SignIn {
    private const val LOGIN_KEY = "login"
    private const val USER_KEY = "user"

    //사용자식별용 index
    private var token : String? = null

    //사용자 토큰 설정
    fun setUserToken(token : String) {
        this.token = token
    }

    //아래코드 : 자동 로그인용
    fun getUser(context : Context) : String {
        val sharedPreferences = context.getSharedPreferences(LOGIN_KEY, Context.MODE_PRIVATE)
        return sharedPreferences.getString(USER_KEY,"") ?:""
    }

    fun setUser(context : Context, user : String) {
        val sharedPreferences = context.getSharedPreferences(LOGIN_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(USER_KEY, user).apply()
    }

    fun clearUser(context : Context) {
        val sharedPreferences = context.getSharedPreferences(LOGIN_KEY, Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
    }


}