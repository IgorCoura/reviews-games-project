package br.com.imt.interfaces

import br.com.imt.models.Games
import io.ktor.http.content.*
import java.io.File

interface IDaoGames {
     fun insert(obj: Games)
     fun update(obj: Games)
     fun delete(id: Int)
     fun get(id: Int): Games
     fun getAll(): List<Games>
     fun updateImg(filePath: String,id: Int)
}