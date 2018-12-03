package com.troy.tersive.server.db.table

import com.troy.tersive.server.db.base.Table
import com.troy.tersive.server.db.getLocalDateTime
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicLong

data class User1(
    val id: Long? = null,
    val created: LocalDateTime = LocalDateTime.now(),
    val modified: LocalDateTime = LocalDateTime.now(),
    val name: String? = null,
    val email: String,
    val passHash: Long,
    val question: String,
    val answer: String
): Table() {

    constructor(rs: ResultSet): this(
        rs.getLong(0),
        rs.getLocalDateTime(1)!!,
        rs.getLocalDateTime(2)!!,
        rs.getString(3),
        rs.getString(4),
        rs.getLong(5),
        rs.getString(6),
        rs.getString(7)
    )

    override fun insert(stmt: PreparedStatement) {
        stmt.setString(0, created.toString())
        stmt.setString(1, modified.toString())
        stmt.setString(2, name)
        stmt.setString(3, email)
        stmt.setLong(4, passHash)
        stmt.setString(5, question)
        stmt.setString(6, answer)
    }

    override fun update(stmt: PreparedStatement) {
        stmt.setString(1, modified.toString())
        stmt.setString(2, name)
        stmt.setString(3, email)
        stmt.setLong(4, passHash)
        stmt.setString(5, question)
        stmt.setString(6, answer)
    }

    companion object {
        const val table = "user"
        const val insertNames = "created, modified, name, email, passHash, question, answer"
        const val insertQs = "?, ?, ?, ?, ?, ?, ?"
        const val updates = "modified = ?, name = ?, email = ?, passHash = ?, question = ?, answer = ?"
        val createTable = """create table $table (
            | id integer autoincrement primary key,
            | created varchar(50) not null,
            | modified varchar(50) not null,
            | name varchar(80),
            | email varchar(128) not null,
            | passHash INTEGER not null,
            | question varchar(128),
            | answer varchar(128)
            | )""".trimMargin()

        var nextId = AtomicLong(0)
    }
}
