package com.troy.tersive.server.db.dao

import com.troy.tersive.server.db.TersiveDatabase
import com.troy.tersive.server.db.table.User1
import java.sql.ResultSet

class UserDao(db: TersiveDatabase) : BaseDao<User1>(db) {

    override val deleteStatement = connection.prepareStatement("delete from user where id = ?")
    override val findStatement = connection.prepareStatement("select * from user where id = ?")
    override val insertStatement get() = connection.prepareStatement("insert into ${User1.table} (${User1.insertNames}) values (${User1.insertQs})")!!
    override val updateStatement get() = connection.prepareStatement("update ${User1.table} set ${User1.updates}")!!

    override fun build(rs: ResultSet) = User1(rs)

    private val findByEmailStatement = connection.prepareStatement("select * from user where email = ?")
    fun findByEmail(email: String): User1? {
        return findByEmailStatement.use {
            it.setString(0, email)
            val rs = it.executeQuery()
            if (rs.next()) User1(rs) else null
        }
    }

    fun save(user: User1) {

    }
}
