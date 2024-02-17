package com.example.userservice

import com.example.userservice.dto.PassportDto
import com.example.userservice.dto.PasswordDto
import com.example.userservice.feign.AuthFeignClient
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController(
    private val authFeignClient: AuthFeignClient
) {

    @GetMapping("/api/v1/users/health")
    fun health(request: HttpServletRequest): String {

        val email = request.getHeader("email")
        val token = request.getHeader("passportToken")
        if (authFeignClient.checkPassport(PassportDto(email, token)).result) {
            println("성공")
        } else {
            println("실패")
        }

        return "Welcome to the UserService!"
    }

}