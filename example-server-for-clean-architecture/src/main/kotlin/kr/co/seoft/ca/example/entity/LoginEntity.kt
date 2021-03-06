package kr.co.seoft.ca.example.entity

data class LoginRequest(val id: String, val password: String)

data class LoginResponse(val complete: Boolean)