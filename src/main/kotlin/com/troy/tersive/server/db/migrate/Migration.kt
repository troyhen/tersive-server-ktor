package com.troy.tersive.server.db.migrate

import java.lang.RuntimeException
import java.sql.Connection

abstract class Migration(val from: Int, val to: Int) {

    init {
        var list = migrations[to]
        if (list == null) {
            val newList = ArrayList<Migration>()
            migrations[to] = newList
            list = newList
        }
        list.add(this)
    }

    val isForward get() = from < to

    abstract fun migrate(conn: Connection)

    companion object {
        val migrations = HashMap<Int, MutableList<Migration>>()

        fun migrate(conn: Connection, from: Int, to: Int) {
            val route = findRoute(from, to) ?: throw RuntimeException("No route found from version $from to $to")
            runMigrations(conn, route)
        }

        fun findRoute(from: Int, to: Int): List<Migration>? {
            if (from == to) return emptyList()
            val routes = ArrayList<Migration>()
            val forward = from < to
            var next = from
            while (next != to) {
                migrations[next]?.forEach { migrate ->
                    if (migrate.isForward == forward) {
                        routes.add(migrate)
                        next = migrate.from
                    }
                }
            }
            return routes
        }

        fun runMigrations(conn: Connection, route: List<Migration>) {
            route.forEach { migration ->
                migration.migrate(conn)
            }
        }
    }
}
