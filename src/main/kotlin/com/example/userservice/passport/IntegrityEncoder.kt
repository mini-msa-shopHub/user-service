package com.example.userservice.passport

import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class IntegrityEncoder {
    companion object{

        private const val ALGORITHM: String = "HmacSHA256"

        fun makePassport(email: String): PassportDto {
            return PassportDto(
                email,
                encodeInfo(email)
            )
        }

        private fun encodeInfo(input: String): String {
            try {
                val instance = Mac.getInstance(ALGORITHM)
                val keySpec = SecretKeySpec("this-is-secret".toByteArray(), ALGORITHM)
                instance.init(keySpec)
                instance.update(input.toByteArray())
                return Base64.getEncoder().encodeToString(instance.doFinal(input.toByteArray()))
            } catch (e: Exception) {
                println(e.message)
                return ""
            }
        }

        fun check(passportDto: PassportDto) {
            if(passportDto.token != encodeInfo(passportDto.email)) {
                throw IllegalArgumentException()
            }
        }
    }

}