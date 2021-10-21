package br.com.imt.routes

import br.com.imt.models.Games
import br.com.imt.models.Review
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.GamesRoutes(){
    route("/games"){
        get{
            var list = mutableListOf<Review>()
            val game = Games(1,"God of War", "Nice Game", "Sony", "Ação",
                99, "dd", "15/02/2010", "Ps3", list)
            call.respond(game)
        }
    }
}

fun Application.registerGamesRoutes(){
    routing {
        GamesRoutes()
    }
}