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
            Log.d("chohee", "실패")
            onError(t)
        }

        override fun onResponse(call: Call<CallWithoutDataExt>, response: Response<CallWithoutDataExt>) {
            if (response.isSuccessful) {
                Log.d("chohee", "응 성공")
                response.body()?.let {
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