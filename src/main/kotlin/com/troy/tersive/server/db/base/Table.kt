package com.troy.tersive.server.db.base

import java.sql.PreparedStatement

abstract class Table {

    abstract fun insert(stmt: PreparedStatement)
    abstract fun update(stmt: PreparedStatement)
}