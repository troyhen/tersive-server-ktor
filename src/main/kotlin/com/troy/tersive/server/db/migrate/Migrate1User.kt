package com.troy.tersive.server.db.migrate

import com.troy.tersive.server.db.table.User1
import java.sql.Connection

object Migrate1User : Migration(1, 2) {
    override fun migrate(conn: Connection) {
        val stmt = conn.createStatement()
        stmt.use {
            stmt.execute(User1.createTable)
        }
    }
}
