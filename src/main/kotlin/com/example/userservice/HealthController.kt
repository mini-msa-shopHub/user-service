package com.example.userservice

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@RestController
class HealthController {

    @GetMapping("/api/v1/users/health")
    fun health(request: HttpServletRequest): String {

        val email = request.getHeader("email")
        val token = request.getHeader("passportToken")
        if (token.equals(createPassportToken(email))) {
            println("성공")
        } else {
            println("실패")
        }

        println(request.getHeader("Authorization"))

        return "Welcome to the UserService!"
    }

    private fun createPassportToken(email: String): String {
        try {
            val instance = Mac.getInstance("HmacSHA256")
            val secretKey = "secretKey"
            val keySpec = SecretKeySpec(secretKey.toByteArray(), "HmacSHA256")
            instance.init(keySpec)
            instance.update(email.toByteArray())
            return Base64.getEncoder().encodeToString(instance.doFinal(email.toByteArray()))
        } catch (e: Exception) {
            println(e.message)
            return ""
        }
    }

}