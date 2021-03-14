package kr.co.seoft.ca.domain.usecase.login

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import kr.co.seoft.ca.domain.entity.LoginRequestEntity
import kr.co.seoft.ca.domain.entity.LoginResponseEntity
import kr.co.seoft.ca.domain.repository.LoginRepository
import kr.co.seoft.ca.domain.usecase.common.RxScheduler
import kr.co.seoft.ca.domain.usecase.common.SUseCase

class RequestLoginUseCase(
    private val loginRepository: LoginRepository,
    override val scheduler: RxScheduler
) : SUseCase<LoginRequestEntity, LoginResponseEntity>() {

    override fun implement(param: LoginRequestEntity): Single<LoginResponseEntity> {
        return loginRepository.requestLogin(param).subscribeOn(Schedulers.io())
    }

}