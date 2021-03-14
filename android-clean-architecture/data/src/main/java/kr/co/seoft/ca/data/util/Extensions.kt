package kr.co.seoft.ca.data.util

import android.util.Log

fun Any.e(tag: String = "") {
    Log.e("$tag#$#", this.toString())
}