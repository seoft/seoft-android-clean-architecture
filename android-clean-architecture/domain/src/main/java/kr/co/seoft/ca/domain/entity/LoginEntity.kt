package kr.co.seoft.ca.domain.entity

data class LoginRequestEntity(val id: String, val password: String)

data class LoginResponseEntity(val complete: Boolean)