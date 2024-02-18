package com.example.userservice.dto

import com.example.userservice.passport.PassportDto

data class UpdateRefreshRequest(
    val email: String,
    val refreshToken: String,
    val passportDto: PassportDto
)
