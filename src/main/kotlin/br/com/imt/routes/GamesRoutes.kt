package br.com.imt.routes

import br.com.imt.dao.GamesDAO
import br.com.imt.models.Games
import br.com.imt.models.Review
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.GamesRoutes(){
    route("/games"){
        get{
            val game = Games(1,"God of War", "Nice Game", "Sony", "Ação",
                99, "dd", "15/02/2010", "Ps3")
            call.respond(game)
        }
    }
}

fun Application.registerGamesRoutes(){
    routing {
        GamesRoutes()
    }
}