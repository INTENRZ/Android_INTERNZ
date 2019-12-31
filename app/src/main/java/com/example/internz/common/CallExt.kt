package com.example.internz.common

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class BaseResponse<T>(
    val message: String,
    val status: Int,
    val success: Boolean
)
fun <T> Call<BaseResponse<T>>.enqueue(
    onError: (Throwable) -> Unit = onStandardError,
    onSuccess: (BaseResponse<T>) -> Unit = {},
    onFail: (Response<BaseResponse<T>>) -> Unit = {
        Log.v("CallError", it.errorBody()?.string()?:"")
        Log.v("CallError", it.code().toString())
        Log.v("CallError", it.toString())
        Log.v("CallError", it.errorBody()?.toString()?:"")

    }
) {
    this.enqueue(object : Callback<BaseResponse<T>> {
        override fun onFailure(call: Call<BaseResponse<T>>, t: Throwable) {
            onError(t)
        }

        override fun onResponse(call: Call<BaseResponse<T>>, response: Response<BaseResponse<T>>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    onSuccess(it)
                } ?: onFail(response)
            } else {
                onFail(response)
            }
        }
    })
}

private val onStandardError: (Throwable) -> Unit = {
    Log.e("CallExt", "network error $it")
}