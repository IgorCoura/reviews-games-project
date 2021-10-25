package br.com.imt.dao

import br.com.imt.models.Review
import br.com.imt.models.User
import java.sql.DriverManager

class ReviewDAO(val connectionString: String) : IBaseDAO<Review>{

    override fun insert(obj: Review){
        val connection = DriverManager.getConnection(connectionString)
        val preparedStatement = connection.prepareStatement("INSERT INTO Review (GameId, UserId, Review, Score, 'Date') VALUES (?,?,?,?,?);")
        preparedStatement.setInt(1, obj.gameId)
        preparedStatement.setInt(2, obj.userId)
        preparedStatement.setString(3, obj.review)
        preparedStatement.setInt(4, obj.score)
        preparedStatement.setString(5, obj.date)
        preparedStatement.executeUpdate()
        connection.close()
    }

    override fun delete(id: Int) {
        val connection = DriverManager.getConnection(connectionString)
        val preparedStatement = connection.prepareStatement("""
            DELETE FROM Review  
            WHERE id = ?;
            """.trimMargin())
        preparedStatement?.setInt(1,id)
        preparedStatement?.executeUpdate()
        connection.close()
    }



    override fun get(id: Int): Review {
        val connection = DriverManager.getConnection(connectionString)
        val sqlStatement = connection.createStatement()
        val resultSet = sqlStatement.executeQuery("SELECT * FROM Review WHERE id == ${id};")
        val review = Review(
            resultSet.getInt("Id"),
            resultSet.getInt("GameId"),
            resultSet.getInt("UserId"),
            resultSet.getString("Review"),
            resultSet.getInt("Score"),
            resultSet.getString("Date")
        )
        resultSet.close()
        sqlStatement.close()
        connection.close()
        return review
    }



    override fun getAll(): List<Review>{
        val connection = DriverManager.getConnection(connectionString)
        val sqlStatement = connection.createStatement()
        val resultSet = sqlStatement.executeQuery("SELECT * FROM Review;")
        var reviews= mutableListOf<Review>()
        while (resultSet.next()){
            val review = Review(
                resultSet.getInt("Id"),
                resultSet.getInt("GameId"),
                resultSet.getInt("UserId"),
                resultSet.getString("Review"),
                resultSet.getInt("Score"),
                resultSet.getString("Date")
            )
            reviews.add(review)
        }
        resultSet.close()
        sqlStatement.close()
        connection.close()
        return reviews
    }
}