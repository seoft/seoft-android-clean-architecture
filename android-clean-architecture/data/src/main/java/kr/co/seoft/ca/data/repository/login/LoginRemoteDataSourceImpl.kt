package kr.co.seoft.ca.data.repository.login

import io.reactivex.Single
import kr.co.seoft.ca.data.api.LoginApi
import kr.co.seoft.ca.data.mapper.LoginDataMapper
import kr.co.seoft.ca.data.repository.login.interfaces.LoginRemoteDataSource
import kr.co.seoft.ca.domain.entity.LoginRequestEntity
import kr.co.seoft.ca.domain.entity.LoginResponseEntity

class LoginRemoteDataSourceImpl(
    private val loginApi: LoginApi,
    private val mapper: LoginDataMapper
) : LoginRemoteDataSource {

    override fun requestLogin(loginRequestEntity: LoginRequestEntity): Single<LoginResponseEntity> {
        return loginApi.requestLogin(mapper.convert(loginRequestEntity)).map { mapper.convert(it) }
    }

}