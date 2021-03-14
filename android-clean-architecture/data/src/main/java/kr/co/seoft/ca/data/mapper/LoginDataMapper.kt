package kr.co.seoft.ca.data.mapper

import kr.co.seoft.ca.data.api.model.LoginRequest
import kr.co.seoft.ca.data.api.model.LoginResponse
import kr.co.seoft.ca.domain.entity.LoginRequestEntity
import kr.co.seoft.ca.domain.entity.LoginResponseEntity

class LoginDataMapper {

    fun convert(loginRequestEntity: LoginRequestEntity): LoginRequest {
        return loginRequestEntity.run { LoginRequest(id, password) }
    }

    fun convert(loginResponse: LoginResponse): LoginResponseEntity {
        return loginResponse.run { LoginResponseEntity(complete) }
    }

}