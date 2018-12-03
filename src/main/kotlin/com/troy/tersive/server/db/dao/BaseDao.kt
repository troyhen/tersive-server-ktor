package com.troy.tersive.server.db.dao

import com.troy.tersive.server.db.base.JdbcDatabase
import com.troy.tersive.server.db.base.Table
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.*

abstract class BaseDao<T : Table>(protected val db: JdbcDatabase) {

    protected val connection: Connection by lazy { db.openConn }
    protected abstract val deleteStatement: PreparedStatement
    protected abstract val findStatement: PreparedStatement
    protected abstract val insertStatement: PreparedStatement
    protected abstract val updateStatement: PreparedStatement

    abstract fun build(rs: ResultSet): T

    fun delete(id: Long): Boolean {
        return deleteStatement.use {
            it.setLong(0, id)
            it.execute()
        }
    }

    fun exists(id: Long): Boolean {
        return findStatement.use {
            it.setLong(0, id)
            it.executeQuery().use { rs ->
                rs.next()
            }
        }
    }

    fun exists(id: UUID): Boolean {
        return findStatement.use {
            it.setString(0, id.toString())
            it.executeQuery().use { rs ->
                rs.next()
            }
        }
    }

    fun find(id: Long): T? {
        return findStatement.use {
            it.setLong(0, id)
            it.executeQuery().use { rs ->
                if (rs.next()) build(rs) else null
            }
        }
    }

    fun find(id: UUID): T? {
        return findStatement.use {
            it.setString(0, id.toString())
            it.executeQuery().use { rs ->
                if (rs.next()) build(rs) else null
            }
        }
    }

    fun insert(row: T): Int {
        return insertStatement.use {
            row.insert(it)
            it.executeUpdate()
        }
    }

    protected fun save(id: Long, row: T) {
        if (exists(id)) {
            update(row)
        } else {
            insert(row)
        }
    }

    fun update(row: T): Int {
        return updateStatement.use {
            row.update(it)
            it.executeUpdate()
        }
    }
}
