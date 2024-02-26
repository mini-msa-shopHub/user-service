package com.example.userservice

import com.example.userservice.passport.IntegrityEncoder
import com.example.userservice.passport.PassportDto
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    @GetMapping("/api/v1/users/health")
    fun health(request: HttpServletRequest): String {

        val email = request.getHeader("email")
        val token = request.getHeader("passportToken")
        IntegrityEncoder.check(PassportDto(email, token))
        return "Welcome to the UserService!"
    }

}