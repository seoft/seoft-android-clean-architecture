package kr.co.seoft.ca.data.repository.login.interfaces

import io.reactivex.Single
import kr.co.seoft.ca.domain.entity.LoginRequestEntity
import kr.co.seoft.ca.domain.entity.LoginResponseEntity

interface LoginRemoteDataSource {

    fun requestLogin(loginRequestEntity: LoginRequestEntity): Single<LoginResponseEntity>

}