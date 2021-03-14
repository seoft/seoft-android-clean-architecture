package kr.co.seoft.ca.ui

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kr.co.seoft.ca.BuildConfig
import kr.co.seoft.ca.util.SafetyLiveData
import kr.co.seoft.ca.util.toSingleEvent

open class BaseViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    protected val _throwable = SafetyLiveData<Throwable>()
    val throwable = _throwable.toSingleEvent()

    private val throwableObserverOnlyDebug by lazy {
        Observer<Throwable> {
            Log.e("ViewModel throwable", it.toString())
            it.printStackTrace()
        }
    }

    init {
        if (BuildConfig.DEBUG) {
            throwable.observeForever(throwableObserverOnlyDebug)
        }
    }

    protected val _showToast = SafetyLiveData<String>()
    val showToast = _showToast.toSingleEvent()

    override fun onCleared() {
        super.onCleared()
        throwable.removeObserver(throwableObserverOnlyDebug)
        compositeDisposable.dispose()
    }

    operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
        this.add(disposable)
    }
}