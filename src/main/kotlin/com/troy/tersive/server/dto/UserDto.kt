package com.troy.tersive.server.dto

import java.util.*

data class UserDto(
        val id: UUID = UUID.randomUUID(),
        val email: String? = null,
        val password: String? = null,
        val password2: String? = null,
        val question: String? = null,
        val answer: String? = null
)
