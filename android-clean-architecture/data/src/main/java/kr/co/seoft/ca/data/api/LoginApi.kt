package kr.co.seoft.ca.data.api

import io.reactivex.Single
import kr.co.seoft.ca.data.api.model.LoginRequest
import kr.co.seoft.ca.data.api.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("api/login")
    fun requestLogin(@Body loginRequest: LoginRequest): Single<LoginResponse>
}