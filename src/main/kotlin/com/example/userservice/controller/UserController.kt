package com.example.userservice.controller

import com.example.userservice.dto.*
import com.example.userservice.passport.IntegrityEncoder
import com.example.userservice.passport.PassportDto
import com.example.userservice.service.UserService
import com.example.userservice.util.CommonResponse
import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService,
) {

    @PostMapping("/join")
    fun joinUser(@RequestBody joinRequest: JoinRequest, request: HttpServletRequest): CommonResponse<UserResponse> {
        return CommonResponse(userService.joinUser(joinRequest))
    }

    private fun checkGatewayPassport(request: HttpServletRequest) {
        val email = request.getHeader("email")
        val passport = request.getHeader("passportToken")
        IntegrityEncoder.check(PassportDto(email, passport))
    }

}