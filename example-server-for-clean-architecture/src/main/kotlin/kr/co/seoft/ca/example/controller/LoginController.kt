package kr.co.seoft.ca.example.controller

import kr.co.seoft.ca.example.entity.LoginRequest
import kr.co.seoft.ca.example.entity.LoginResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class LoginController {

    private val logger = LoggerFactory.getLogger(javaClass.name)

    val loginData = mapOf(
            "this_is_id" to "this_is_password",
            "seoft" to "qwer1234",
            "aaa" to "aaa"
    )

    @PostMapping("/login")
    fun requestLogin(loginRequest: LoginRequest): LoginResponse {
        return LoginResponse(loginData[loginRequest.id] == loginRequest.password)
    }

}