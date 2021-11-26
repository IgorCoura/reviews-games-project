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
        val preparedStatement = connection.prepareStatement("INSERT INTO User (Name, Email, Password, Img, Salt) " +
                "VALUES (?,?,?,?,?);")
        preparedStatement.setString(1, obj.name)
        preparedStatement.setString(2, obj.email)
        preparedStatement.setString(3, obj.password)
        preparedStatement.setString(4, obj.email)
        preparedStatement.setString(5, obj.salt)
        preparedStatement.executeUpdate()
        connection.close()
    }

    override fun update(obj: User) {
        val connection = DriverManager.getConnection(connectionString)
        val preparedStatement = connection.prepareStatement("""
            UPDATE User 
            SET Name = ?, Email = ?, Password =?, Img = ?, Salt = ? 
            WHERE Id = ?;
            """.trimMargin())
        preparedStatement.setString(1, obj.name)
        preparedStatement.setString(2, obj.email)
        preparedStatement.setString(3, obj.password)
        preparedStatement.setString(4, obj.img)
        preparedStatement.setString(4, obj.salt)
        preparedStatement.setInt(5, obj.id)
        preparedStatement.executeUpdate()
        connection.close()
    }

    override fun updateImg(filePath: String, id: Int){
        val connection = DriverManager.getConnection(connectionString)
        val preparedStatement = connection.prepareStatement("""
            UPDATE User 
            SET Img = ? 
            WHERE Id = ?;
            """.trimMargin())
        preparedStatement.setString(1, filePath)
        preparedStatement.setInt(2, id)
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
            resultSet.getString("Img"),
            resultSet.getString("Salt"),
        )
        resultSet.close()
        sqlStatement.close()
        connection.close()
        return u
    }

    override fun get(email:String, password: String): User{
        val connection = DriverManager.getConnection(connectionString)
        val preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE Email = ? AND Password = ?")
        preparedStatement.setString(1, email)
        preparedStatement.setString(2, password)
        val resultSet = preparedStatement.executeQuery()
        val u = User(
            resultSet.getInt("Id"),
            resultSet.getString("Name"),
            resultSet.getString("Email"),
            resultSet.getString("Password"),
            resultSet.getString("Img"),
            resultSet.getString("Salt")
        )
        resultSet.close()
        preparedStatement.close()
        connection.close()
        return u
    }

    override fun getSalt(email:String): String{
        val connection = DriverManager.getConnection(connectionString)
        val preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE Email = ?")
        preparedStatement.setString(1, email)
        val resultSet = preparedStatement.executeQuery()
        val salt = resultSet.getString("Salt")
        resultSet.close()
        preparedStatement.close()
        connection.close()
        return salt
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
                resultSet.getString("Img"),
                resultSet.getString("Salt")
            )
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
        val resultSet = sqlStatement.executeQuery("SELECT * FROM User INNER JOIN Review ON Review.UserId = User.Id INNER JOIN Games ON Games.Id = Review.GameId WHERE User.Id = ${id};")
        val user = User(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4),
            resultSet.getString(5),
            resultSet.getString(6)
        )
        var reviews= mutableListOf<Review>()
        while (resultSet.next()){
            val game = Games(
                resultSet.getInt(13),
                resultSet.getString(14),
                resultSet.getString(15),
                resultSet.getString(16),
                resultSet.getString(17),
                resultSet.getInt(18),
                resultSet.getString(19),
                resultSet.getString(20),
                resultSet.getString(21)
            )
            val review = Review(
                resultSet.getInt(7),
                resultSet.getInt(8),
                resultSet.getInt(9),
                resultSet.getString(10),
                resultSet.getInt(11),
                resultSet.getString(12),
                game
            )
            reviews.add(review)
        }
        user.reviews = reviews
        resultSet.close()
        sqlStatement.close()
        connection.close()
        return user
    }
}