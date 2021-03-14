package kr.co.seoft.ca.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData

fun String.toast(context: Context, duration: Int = Toast.LENGTH_LONG): Toast {
    return Toast.makeText(context, this, duration).apply { show() }
}

fun Any.e(tag: String = "") {
    Log.e("$tag#$#", this.toString())
}

fun <T> LiveData<T>.toSingleEvent(): LiveData<T> {
    val result = LiveEvent<T>()
    result.addSource(this) {
        result.value = it
    }
    return result
}