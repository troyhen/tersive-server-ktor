package com.troy.tersive.server.handler

import com.troy.tersive.server.dto.LoginDto
import com.troy.tersive.server.dto.UserDto
import com.troy.tersive.server.dto.UserResultDto

object UserHandler {
    fun delete(user: UserDto) {

    }

    fun login(user: LoginDto) {

    }

    fun register(user: UserDto): UserResultDto {
        return user.run {
            val at = email?.indexOf('@') ?: -1
            val emailLength = email?.length ?: -1
            val passLength = password?.length ?: -1
            when {
                email.isNullOrBlank() ||
                        at < 1 ||
                        at == emailLength - 1 ||
                        emailLength < 3 ||
                        passLength < 3 ||
                        password.isNullOrBlank() -> UserResultDto.invalid("Invalid login")
                else -> {
                    UserResultDto.success()
                }
            }
        }
    }

    fun update(user: UserDto) {

    }
}
