package kr.co.seoft.ca.data.repository.login

import io.reactivex.Single
import kr.co.seoft.ca.data.repository.login.interfaces.LoginRemoteDataSource
import kr.co.seoft.ca.domain.entity.LoginRequestEntity
import kr.co.seoft.ca.domain.entity.LoginResponseEntity
import kr.co.seoft.ca.domain.repository.LoginRepository

class LoginRepositoryImpl(private val remoteDataSource: LoginRemoteDataSource) : LoginRepository {
    override fun requestLogin(loginRequestEntity: LoginRequestEntity): Single<LoginResponseEntity> {
        return remoteDataSource.requestLogin(loginRequestEntity)
    }
}