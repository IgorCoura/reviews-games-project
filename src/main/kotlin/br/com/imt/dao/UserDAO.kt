package br.com.imt.dao

import br.com.imt.models.User
import java.sql.DriverManager

class UserDAO(val connectionString: String): IBaseDAO<User>{
    override fun insert(obj: User){
        val connection = DriverManager.getConnection(connectionString)
        val preparedStatement = connection.prepareStatement("INSERT INTO User (Name, Email, Password, Img) " +
                "VALUES (?,?,?,?);")
        preparedStatement.setString(1, obj.name)
        preparedStatement.setString(2, obj.email)
        preparedStatement.setString(3, obj.password)
        preparedStatement.setString(4, obj.email)
        preparedStatement.executeUpdate()
        connection.close()
    }

    override fun update(obj: User) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        val connection = DriverManager.getConnection(connectionString)
        val preparedStatement = connection.prepareStatement("""
            DELETE FROM User  
            WHERE id = ?;
            """.trimMargin())
        preparedStatement?.setInt(1,id)
        preparedStatement?.executeUpdate()
        connection.close()
    }



    override fun get(id: Int): User {
        val connection = DriverManager.getConnection(connectionString)
        val sqlStatement = connection.createStatement()
        val resultSet = sqlStatement.executeQuery("SELECT * FROM User WHERE id == ${id};")
        val u = User(
            resultSet.getInt("Id"),
            resultSet.getString("Name"),
            resultSet.getString("Email"),
            resultSet.getString("Password"),
            resultSet.getString("Img")
        )
        resultSet.close()
        sqlStatement.close()
        connection.close()
        return u
    }



    override fun getAll(): List<User>{
        val connection = DriverManager.getConnection(connectionString)
        val sqlStatement = connection.createStatement()
        val resultSet = sqlStatement.executeQuery("SELECT * FROM User;")
        var user = mutableListOf<User>()
        while (resultSet.next()){
            val u = User(
                resultSet.getInt("Id"),
                resultSet.getString("Name"),
                resultSet.getString("Email"),
                resultSet.getString("Password"),
                resultSet.getString("Img"))
            user.add(u)
        }
        resultSet.close()
        sqlStatement.close()
        connection.close()
        return user
    }
}