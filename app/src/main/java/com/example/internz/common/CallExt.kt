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
    this.enqueue(object : Callback<BaseResponse<T>> {
        override fun onFailure(call: Call<BaseResponse<T>>, t: Throwable) {
            onError(t)
        }

        override fun onResponse(call: Call<BaseResponse<T>>, response: Response<BaseResponse<T>>) {
            if (response.isSuccessful) {
                response.body()?.data?.let {
                    onSuccess(it)
                } ?: onFail(response.body()?.status?:-1, response.body()?.message.orEmpty())
            } else {
                onFail(response.body()?.status?:-1, response.body()?.message.orEmpty())
            }
        }
    })
}

private val onStandardError: (Throwable) -> Unit = {
    Log.e("CallExt", "network error $it")
}