package kr.co.seoft.ca.domain.repository

import io.reactivex.Single
import kr.co.seoft.ca.domain.entity.LoginRequestEntity
import kr.co.seoft.ca.domain.entity.LoginResponseEntity

interface LoginRepository {
    fun requestLogin(loginRequestEntity: LoginRequestEntity) : Single<LoginResponseEntity>
}