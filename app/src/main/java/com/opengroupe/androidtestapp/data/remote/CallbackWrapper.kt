
package com.opengroupe.androidtestapp.data.remote

import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class CallbackWrapper<T : Any> : DisposableObserver<T>() {

    protected abstract fun onSuccess(t: T)

    protected abstract fun onError(any: Any)

    override fun onNext(t: T) {
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        when (e) {
            is HttpException -> {
                val responseBody = (e).response().errorBody()
                responseBody?.let {
                    onError(getErrorMessage(it))
                }
            }
            is SocketTimeoutException -> {
                onError("SocketTimeoutException")
            }
            is IOException -> {
                onError("NetworkException")
            }
            else -> {
                e.message?.let {
                    onError("UnknownException")
                }
            }
        }
    }


    override fun onComplete() {

    }

    private fun getErrorMessage(responseBody: ResponseBody): String {
        val jsonObject = JSONObject(responseBody.string())
        return jsonObject.getString(("message"))
    }
}