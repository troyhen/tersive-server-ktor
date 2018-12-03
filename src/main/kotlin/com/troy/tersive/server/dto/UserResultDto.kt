package com.troy.tersive.server.dto

data class UserResultDto(val status: Status, val message: String? = null, val data: Map<String, String>? = null) {
    enum class Status {
        SUCCESS, INVALID, ERROR
    }

    companion object {
        fun error(message: String? = null) = UserResultDto(Status.ERROR, message)
        fun invalid(message: String? = null) = UserResultDto(Status.INVALID, message)
        fun success(data: Map<String, String>? = null) = UserResultDto(Status.SUCCESS, data = data)
    }
}
