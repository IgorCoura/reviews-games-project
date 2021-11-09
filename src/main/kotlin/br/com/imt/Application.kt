package br.com.imt

import br.com.imt.dao.GamesDAO
import br.com.imt.dao.ReviewDAO
import br.com.imt.dao.UserDAO
import br.com.imt.routes.registerGamesRoutes
import br.com.imt.routes.registerManageRoutes
import br.com.imt.routes.registerReviewRoutes
import br.com.imt.routes.registerUserRoutes
import br.com.imt.service.GamesService
import br.com.imt.service.ReviewService
import br.com.imt.service.UserService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module(){

    val connectionString = "jdbc:sqlite:ReviewsGamesDbV2.db"
    install(ContentNegotiation){
        json()
    }
    install(Authentication){
        jwt("auth-user") {
            realm = JwtConfig.realmUser
            verifier(JwtConfig.userJwtVerifier)
            validate { credential ->
                    JWTPrincipal(credential.payload)
            }
        }
        jwt ("auth-manager"){
            realm = JwtConfig.realmManager
            verifier(JwtConfig.managerJwtVerifier)
            validate { credential ->
                JWTPrincipal(credential.payload)
            }
        }
    }
    registerManageRoutes()
    registerGamesRoutes(GamesService(GamesDAO(connectionString)))
    registerUserRoutes(UserService(UserDAO(connectionString)))
    registerReviewRoutes(ReviewService(ReviewDAO(connectionString)))
}