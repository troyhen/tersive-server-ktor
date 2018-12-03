package com.troy.tersive.server.db.table

import com.troy.tersive.server.db.base.Table
import com.troy.tersive.server.db.getInteger
import java.sql.PreparedStatement
import java.sql.ResultSet

data class Tersive1(
    val id: Long,
    val phrase: String,
    val lvl1: String?,
    val lvl2: String?,
    val lvl3: String?,
    val lvl4: String?,
    val kbd: String?,
    val words: Int,
    val frequency: Int?,
    val sort: Int?,
    val type: Type
) : Table() {

    constructor(rs: ResultSet) : this(
        rs.getLong(0),
        rs.getString(1),
        rs.getString(2),
        rs.getString(3),
        rs.getString(4),
        rs.getString(5),
        rs.getString(6),
        rs.getInt(7),
        rs.getInteger(8),
        rs.getInteger(9),
        Type.fromOrdinal(rs.getInt(10))
    )

    override fun insert(stmt: PreparedStatement) {
        stmt.setString(0, phrase)
        stmt.setString(1, lvl1)
        stmt.setString(2, lvl2)
        stmt.setString(3, lvl3)
        stmt.setString(4, lvl4)
        stmt.setString(5, kbd)
        stmt.setInt(6, words)
        stmt.setObject(7, frequency)
        stmt.setObject(8, sort)
        stmt.setInt(9, type.ordinal)
    }

    override fun update(stmt: PreparedStatement) {
        stmt.setString(0, phrase)
        stmt.setString(1, lvl1)
        stmt.setString(2, lvl2)
        stmt.setString(3, lvl3)
        stmt.setString(4, lvl4)
        stmt.setString(5, kbd)
        stmt.setInt(6, words)
        stmt.setObject(7, frequency)
        stmt.setObject(8, sort)
        stmt.setInt(9, type.ordinal)
    }

    companion object {
        const val table = "tersive"
        const val insertNames = "phrase, lvl1, lvl2, lvl3, lvl4, kbd, words, frequency, sort, type"
        const val insertQs = "?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
        const val updates =
            "phrase = ?, lvl1 = ?, lvl2 = ?, lvl3 = ?, lvl4 = ?, kbd = ?, words = ?, frequency = ?, sort = ?, type= ?"
        val createTable =
            """create table $table (
                | id integer autoincrement primary key,
                | phrase text not null,
                | lvl1 text,
                | lvl2 text,
                | lvl3 text,
                | lvl4 text,
                | kbd text,
                | words integer not null,
                | frequency integer not null,
                | sort integer,
                | type integer not null
                | )""".trimMargin()
    }

    enum class Type {
        WORD, PHRASE, RELIGIOUS_WORD, RELIGIONS_PHRASE;

        companion object {
            fun fromOrdinal(ordinal: Int) = values()[ordinal]
        }
    }
}
