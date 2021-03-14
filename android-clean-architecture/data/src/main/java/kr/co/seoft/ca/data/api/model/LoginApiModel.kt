package kr.co.seoft.ca.data.api.model

data class LoginRequest(val id: String, val password: String)

data class LoginResponse(val complete: Boolean)