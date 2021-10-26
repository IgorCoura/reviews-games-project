package br.com.imt.dao

import br.com.imt.dto.UserDTO
import br.com.imt.interfaces.IDaoUser
import br.com.imt.models.Games
import br.com.imt.models.Review
import br.com.imt.models.User
import java.sql.DriverManager

class UserDAO(val connectionString: String): IDaoUser{

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
        val connection = DriverManager.getConnection(connectionString)
        val preparedStatement = connection.prepareStatement("""
            UPDATE User 
            SET Name = ?, Email = ?, Password =?, Img = ? 
            WHERE Id = ?;
            """.trimMargin())
        preparedStatement.setString(1, obj.name)
        preparedStatement.setString(2, obj.email)
        preparedStatement.setString(3, obj.password)
        preparedStatement.setString(4, obj.img)
        preparedStatement.setInt(5, obj.id)
        preparedStatement.executeUpdate()
        connection.close()
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

    override fun getWithReviews(id: Int): User{
        val connection = DriverManager.getConnection(connectionString)
        val sqlStatement = connection.createStatement()
        val resultSet = sqlStatement.executeQuery("SELECT * FROM User WHERE Id = ${id};")
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
        val connectionReview = DriverManager.getConnection(connectionString)
        val sqlStatementReview  = connectionReview.createStatement()
        val resultSetReview  = sqlStatementReview.executeQuery("SELECT * FROM Review WHERE UserId = ${u.id};")
        var reviews= mutableListOf<Review>()
        while (resultSetReview.next()){
            val review = Review(
                resultSetReview.getInt("Id"),
                resultSetReview.getInt("GameId"),
                resultSetReview.getInt("UserId"),
                resultSetReview.getString("Review"),
                resultSetReview.getInt("Score"),
                resultSetReview.getString("Date")
            )
            reviews.add(review)
        }
        u.reviews = reviews
        resultSetReview.close()
        sqlStatement.close()
        connection.close()
        return u
    }
}