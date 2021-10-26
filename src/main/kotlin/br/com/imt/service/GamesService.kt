package br.com.imt.service

import br.com.imt.dao.IBaseDAO
import br.com.imt.dto.CreateGamesDTO
import br.com.imt.dto.GameWithReviewsDTO
import br.com.imt.dto.GamesDTO
import br.com.imt.dto.ReviewDTO
import br.com.imt.models.Games
import br.com.imt.models.Review


class GamesService(val dao: IBaseDAO<Games>) {

    fun insert(obj: CreateGamesDTO){
        val game = Games(0, obj.name, obj.summary, obj.developer, obj.genre, obj.score, obj.img, obj.release, obj.consoles)
        dao.insert(game)
    }

    fun update(obj: GamesDTO){
        val game = Games(obj.id, obj.name, obj.summary, obj.developer, obj.genre, obj.score, obj.img, obj.release, obj.consoles)
        dao.update(game)
    }

    fun getAll(): List<GamesDTO>{
        val games = dao.getAll()
        return games.map{g -> GamesDTO(g.id, g.name, g.summary,g.developer,g.genre,g.score,g.img,g.release,g.consoles)}
    }

    fun get(id: String): GameWithReviewsDTO{
        val g = dao.get(id.toInt())
        val dto = GameWithReviewsDTO(g.id, g.name, g.summary,g.developer,g.genre,g.score,g.img,g.release,g.consoles)
        dto.reviews = g.reviews?.map{ r -> ReviewDTO(r.id, r.gameId, r.userId, r.review, r.score, r.date) };
        return dto;
    }

    fun delete(id: String){
        dao.delete(id.toInt())
    }
}