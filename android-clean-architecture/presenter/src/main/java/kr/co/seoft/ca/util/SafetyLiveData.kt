package kr.co.seoft.ca.util

import android.os.Looper
import androidx.lifecycle.MutableLiveData
import io.reactivex.functions.Consumer

class SafetyLiveData<T> : MutableLiveData<T>, Consumer<T>, (T) -> Unit {
    constructor(value: T) : super(value)
    constructor() : super()

    override fun accept(t: T) {
        set(t)
    }

    fun notifyChange() {
        value?.run {
            set(this)
        }
    }

    fun set(value: T) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            setValue(value)
        } else {
            postValue(value)
        }
    }

    override fun invoke(t: T) {
        set(t)
    }

}