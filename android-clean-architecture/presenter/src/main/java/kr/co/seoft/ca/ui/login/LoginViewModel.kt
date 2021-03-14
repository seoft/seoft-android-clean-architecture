package kr.co.seoft.ca.ui.login

import kr.co.seoft.ca.domain.entity.LoginRequestEntity
import kr.co.seoft.ca.domain.usecase.login.RequestLoginUseCase
import kr.co.seoft.ca.ui.BaseViewModel
import kr.co.seoft.ca.util.SafetyLiveData
import kr.co.seoft.ca.util.toSingleEvent

class LoginViewModel(
    private val requestLoginUseCase: RequestLoginUseCase
) : BaseViewModel() {

    val id = SafetyLiveData("aaa")
    val password = SafetyLiveData("aaa")

    private val _loginStatus = SafetyLiveData<Boolean>()
    val loginComplete = _loginStatus.toSingleEvent()

    fun onLogin() {
        val stableId = id.value ?: return
        val stablePassword = password.value ?: return

        compositeDisposable += requestLoginUseCase.execute(
            LoginRequestEntity(stableId, stablePassword)
        ).subscribe({
            _loginStatus.set(it.complete)
        }, _throwable)
    }

}