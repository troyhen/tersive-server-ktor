package com.troy.tersive.server.db.base

import java.util.*

class DatabaseBuilder {

    var properties: Properties? = null
        set(value) {
            field = value
            if (value != null) {
                driver = value.getProperty("driver") ?: driver
                url = value.getProperty("url") ?: url
                username = value.getProperty("username") ?: username
                password = value.getProperty("password") ?: password
            }
        }
    var driver: String? = null
    var url: String? = null
    var username: String? = null
    var password: String? = null
}
