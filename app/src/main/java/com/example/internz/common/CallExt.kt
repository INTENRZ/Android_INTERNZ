package com.example.internz.common

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class BaseResponse<T>(
    val message: String,
    val status: Int,
    val success: Boolean,
    val data: T?
)

fun <T> Call<BaseResponse<T>>.enqueue(
    onError: (Throwable) -> Unit = onStandardError,
    onSuccess: (T) -> Unit = {},
    onFail: (status: Int, message: String) -> Unit = {_, _ -> Unit}
) {
    //ㅇㅋ
    this.enqueue(object : Callback<BaseResponse<T>> {
        override fun onFailure(call: Call<BaseResponse<T>>, t: Throwable) {
            Log.d("chohee", "실패")
            onError(t)
        }

        override fun onResponse(call: Call<BaseResponse<T>>, response: Response<BaseResponse<T>>) {
            if (response.isSuccessful) {
                Log.d("chohee", "응 성공")
                response.body()?.data?.let {
                    Log.d("chohee", it.toString())
                    onSuccess(it)

                } ?: onFail(response.body()?.status?:-1, response.body()?.message.orEmpty())
            } else {
                Log.d("chohee", "시불탱")
                onFail(response.body()?.status?:-1, response.body()?.message.orEmpty())
            }
        }
    })
}

private val onStandardError: (Throwable) -> Unit = {
    Log.e("CallExt", "network error $it")
}