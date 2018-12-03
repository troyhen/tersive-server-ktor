package com.troy.tersive.server.dto

data class TersiveDto(
        val flags: Int,
        val phrase: String,
        val script: String,
        val key: String,
        val frequency: Int
)
