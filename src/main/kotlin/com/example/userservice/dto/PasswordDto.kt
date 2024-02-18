package com.example.userservice.dto

import com.example.userservice.passport.PassportDto

data class PasswordDto(
    val value: String,
    val passportDto: PassportDto
)
