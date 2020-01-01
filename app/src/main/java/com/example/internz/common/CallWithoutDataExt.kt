package com.example.internz.common

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class CallWithoutDataExt(
    val message: String,
    val status: Int,
    val success: Boolean
)


fun Call<CallWithoutDataExt>.enqueue(
    onError: (Throwable) -> Unit = onStandardError,
    onSuccess: (CallWithoutDataExt) -> Unit = {},
    onFail: (status: Int, message: String) -> Unit = {_, _ -> Unit}
) {
    //ㅇㅋ
    this.enqueue(object : Callback<CallWithoutDataExt> {
        override fun onFailure(call: Call<CallWithoutDataExt>, t: Throwable) {
            Log.e("TAG", "onFailure")
            onError(t)
        }

        override fun onResponse(call: Call<CallWithoutDataExt>, response: Response<CallWithoutDataExt>) {
            if (response.isSuccessful) {
                response.body()?.let {
                    onSuccess(it)
                    Log.e("TAG", "${it.toString()}")
                } ?: onFail(response.body()?.status?:-1, response.body()?.message.orEmpty())
            } else {
                Log.e("TAG", "onFail2")
                onFail(response.body()?.status?:-1, response.body()?.message.orEmpty())
            }
        }
    })
}

private val onStandardError: (Throwable) -> Unit = {
    Log.e("CallExt", "network error $it")
}