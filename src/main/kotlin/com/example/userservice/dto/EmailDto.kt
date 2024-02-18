package com.example.userservice.dto

import com.example.userservice.passport.PassportDto

data class EmailDto(
    val value: String,
    val passportDto: PassportDto
)
