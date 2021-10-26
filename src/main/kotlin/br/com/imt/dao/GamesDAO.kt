package br.com.imt.dao

import br.com.imt.models.Games
import br.com.imt.models.Review
import java.sql.DriverManager

class GamesDAO (val connectionString: String): IBaseDAO<Games>{

    override fun insert(obj: Games){
        val connection = DriverManager.getConnection(connectionString)
        val preparedStatement = connection.prepareStatement("INSERT INTO Games (Name, Summary, Developer, Genre, Score, Img, Release, Consoles) " +
                "VALUES (?,?,?,?,?,?,?,?);")
        preparedStatement.setString(1, obj.name)
        preparedStatement.setString(2, obj.summary)
        preparedStatement.setString(3, obj.developer)
        preparedStatement.setString(4, obj.genre)
        preparedStatement.setInt(5, obj.score)
        preparedStatement.setString(6, obj.img)
        preparedStatement.setString(7, obj.release)
        preparedStatement.setString(8, obj.consoles)
        preparedStatement.executeUpdate()
        connection.close()
    }

    override fun update(obj: Games) {
        val connection = DriverManager.getConnection(connectionString)
        val preparedStatement = connection.prepareStatement("""
            UPDATE Games 
            SET Name = ?, Summary =?, Developer=?, Genre=?, Score=?, Img=?, Release=?, Consoles=? 
            WHERE Id = ?;
            """.trimMargin())
        preparedStatement.setString(1, obj.name)
        preparedStatement.setString(2, obj.summary)
        preparedStatement.setString(3, obj.developer)
        preparedStatement.setString(4, obj.genre)
        preparedStatement.setInt(5, obj.score)
        preparedStatement.setString(6, obj.img)
        preparedStatement.setString(7, obj.release)
        preparedStatement.setString(8, obj.consoles)
        preparedStatement.setInt(9, obj.id)
        preparedStatement.executeUpdate()
        connection.close()
    }

    override fun delete(id: Int) {
        val connection = DriverManager.getConnection(connectionString)
        val preparedStatement = connection.prepareStatement("""
            DELETE FROM Games  
            WHERE id = ?;
            """.trimMargin())
        preparedStatement?.setInt(1,id)
        preparedStatement?.executeUpdate()
        connection.close()
    }



    override fun get(id: Int): Games{
        val connection = DriverManager.getConnection(connectionString)
        val sqlStatement = connection.createStatement()
        val resultSet = sqlStatement.executeQuery("SELECT * FROM Games WHERE Id = ${id};")
        val game = Games(
            resultSet.getInt("Id"),
            resultSet.getString("Name"),
            resultSet.getString("Summary"),
            resultSet.getString("Developer"),
            resultSet.getString("Genre"),
            resultSet.getInt("Score"),
            resultSet.getString("Img"),
            resultSet.getString("Release"),
            resultSet.getString("Consoles")
        )
        resultSet.close()
        sqlStatement.close()
        connection.close()
        val connectionReview = DriverManager.getConnection(connectionString)
        val sqlStatementReview  = connectionReview.createStatement()
        val resultSetReview  = sqlStatementReview.executeQuery("SELECT * FROM Review WHERE GameId = ${game.id};")
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
        game.reviews = reviews
        resultSetReview.close()
        sqlStatement.close()
        connection.close()
        return game
    }

    override fun getAll(): List<Games>{
        val connection = DriverManager.getConnection(connectionString)
        val sqlStatement = connection.createStatement()
        val resultSet = sqlStatement.executeQuery("SELECT * FROM Games;")
        var games = mutableListOf<Games>()
        while (resultSet.next()){
            var g = Games(
                resultSet.getInt("Id"),
                resultSet.getString("Name"),
                resultSet.getString("Summary"),
                resultSet.getString("Developer"),
                resultSet.getString("Genre"),
                resultSet.getInt("Score"),
                resultSet.getString("Img"),
                resultSet.getString("Release"),
                resultSet.getString("Consoles"),
            )
            games.add(g)
        }
        resultSet.close()
        sqlStatement.close()
        connection.close()
        return  games
    }
}