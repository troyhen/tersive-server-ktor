package com.troy.tersive.server.db

import com.troy.tersive.server.db.base.DatabaseBuilder
import com.troy.tersive.server.db.base.JdbcDatabase
import com.troy.tersive.server.db.dao.UserDao

class TersiveDatabase(builder: DatabaseBuilder): JdbcDatabase(builder) {
    val userDao = UserDao(this)
}
