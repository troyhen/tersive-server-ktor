@file:Suppress("unused")

package com.troy.tersive.server.db

import java.sql.ResultSet
import java.time.LocalDate
import java.time.LocalDateTime

fun ResultSet.getInteger(column: Int): Int? {
    val value = getInt(column)
    return if (wasNull()) null else value
}

fun ResultSet.getLocalDate(column: Int) = getString(column).getLocalDate()
fun String?.getLocalDate() = this?.let { LocalDate.parse(it) }

fun ResultSet.getLocalDateTime(column: Int) = getString(column).getLocalDateTime()
fun String?.getLocalDateTime() = this?.let { LocalDateTime.parse(it) }
