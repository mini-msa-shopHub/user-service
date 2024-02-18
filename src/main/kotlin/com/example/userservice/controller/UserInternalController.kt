package com.example.userservice.controller

import com.example.userservice.dto.EmailDto
import com.example.userservice.dto.UpdateRefreshRequest
import com.example.userservice.dto.UserInfo
import com.example.userservice.passport.IntegrityEncoder
import com.example.userservice.passport.PassportDto
import com.example.userservice.service.UserService
import com.example.userservice.util.CommonResponse
import com.example.userservice.util.EmptyDto
import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
class UserInternalController(
    private val userService: UserService
) {

    @GetMapping("/user-info")
    fun getUserInfo(@RequestBody emailDto: EmailDto, request: HttpServletRequest): CommonResponse<UserInfo> {
        checkPassport(emailDto.passportDto)
        return CommonResponse(userService.getUserInfo(emailDto))
    }

    @PatchMapping("/refresh-token")
    fun updateRefreshToken(@RequestBody updateRefreshRequest: UpdateRefreshRequest): CommonResponse<EmptyDto> {
        checkPassport(updateRefreshRequest.passportDto)
        userService.updateRefreshToken(updateRefreshRequest)
        return CommonResponse.EMPTY
    }

    private fun checkPassport(passportDto: PassportDto) {
        return IntegrityEncoder.check(passportDto)
    }

}