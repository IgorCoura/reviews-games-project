package br.com.imt.dao

import br.com.imt.interfaces.IDaoGames
import br.com.imt.models.Games
import br.com.imt.models.Review
import br.com.imt.models.User
import java.sql.DriverManager

class GamesDAO (val connectionString: String): IDaoGames {

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

    override fun updateImg(filePath: String,id: Int) {
        val connection = DriverManager.getConnection(connectionString)
        val preparedStatement = connection.prepareStatement("""
            UPDATE Games 
            SET Img=?
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
        val resultSet = sqlStatement.executeQuery("SELECT * FROM Games INNER JOIN Review ON Review.GameId = Games.Id INNER JOIN User ON User.Id = Review.UserId WHERE Games.Id = ${id};")
        val game = Games(
            resultSet.getInt(1),
            resultSet.getString(2),
            resultSet.getString(3),
            resultSet.getString(4),
            resultSet.getString(5),
            resultSet.getInt(6),
            resultSet.getString(7),
            resultSet.getString(8),
            resultSet.getString(9)
        )
        var reviews= mutableListOf<Review>()
        while (resultSet.next()){
            val user = User(
                resultSet.getInt(16),
                resultSet.getString(17),
                resultSet.getString(18),
                resultSet.getString(19),
                resultSet.getString(20),
                resultSet.getString(21)
            )
            val review = Review(
                resultSet.getInt(10),
                resultSet.getInt(11),
                resultSet.getInt(12),
                resultSet.getString(13),
                resultSet.getInt(14),
                resultSet.getString(15),
                null,
                user
            )
            reviews.add(review)
        }
        game.reviews = reviews
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