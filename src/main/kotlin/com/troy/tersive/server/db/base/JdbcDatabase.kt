package com.troy.tersive.server.db.base

import java.lang.RuntimeException
import java.sql.Connection
import java.sql.DriverManager

@Suppress("MemberVisibilityCanBePrivate", "unused")
abstract class JdbcDatabase(private val builder: DatabaseBuilder) {

    var conn: Connection? = null
    val isOpen get() = conn != null
    val openConn: Connection
        get() {
            if (!isOpen) {
                open()
            }
            return conn!!
        }

    fun close() {
        conn?.close()
        conn = null
    }

    fun open() {
        if (conn == null) {
            val driver = builder.driver ?: throw RuntimeException("Database driver not specified")
            val url = builder.url ?: throw RuntimeException("Database url not specified")
            val username = builder.username
            val password = builder.password
            Class.forName(driver)
            conn = DriverManager.getConnection(url, username, password)
        }
    }
}
