package br.com.imt

import br.com.imt.dao.GamesDAO
import br.com.imt.dao.ReviewDAO
import br.com.imt.dao.UserDAO
import br.com.imt.models.Games
import br.com.imt.models.Review
import br.com.imt.models.User
import br.com.imt.routes.registerGamesRoutes
import br.com.imt.routes.registerUserRoutes
import br.com.imt.service.GamesService
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import java.sql.DriverManager

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module(){
    val connectionString = "jdbc:sqlite:ReviewsGamesDb.db"
    install(ContentNegotiation){
        json()
    }
    registerGamesRoutes(GamesService(GamesDAO(connectionString)))
    registerUserRoutes()
}