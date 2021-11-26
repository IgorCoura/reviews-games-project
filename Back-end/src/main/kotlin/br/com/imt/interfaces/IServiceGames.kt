package br.com.imt.interfaces

import br.com.imt.dto.CreateGamesDTO
import br.com.imt.dto.GameWithReviewsDTO
import br.com.imt.dto.GamesDTO
import br.com.imt.dto.ReviewDTO
import br.com.imt.models.Games
import io.ktor.http.content.*
import java.io.File

interface IServiceGames {
    fun insert(obj: CreateGamesDTO)
    fun update(obj: GamesDTO)
    fun getAll(): List<GamesDTO>
    fun get(id: String): GameWithReviewsDTO
    fun delete(id: String)
    fun getImg(id: String): File
    suspend fun saveImg(multipartData: MultiPartData, id: String): String
}