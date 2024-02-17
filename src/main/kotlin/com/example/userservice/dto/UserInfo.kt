package com.example.userservice.dto

data class UserInfo(
    val id: Long,
    val email: String,
    val password:String,
    val userRole: String
)
