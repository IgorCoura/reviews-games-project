package br.com.imt

import br.com.imt.dao.GamesDAO
import br.com.imt.dao.ReviewDAO
import br.com.imt.dao.UserDAO
import br.com.imt.routes.*
import br.com.imt.service.GamesService
import br.com.imt.service.ReviewService
import br.com.imt.service.UserService
import br.com.imt.component.JwtComponent
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, (System.getenv("PORT")?:"8080").toInt()) {
        module()
    }.start(wait = true)
}

fun Application.module(){

    val connectionString = "jdbc:sqlite:ReviewsGamesDbV2.db"
    install(ContentNegotiation){
        json()
    }
    install(CORS){
        anyHost()
        header(HttpHeaders.ContentType)
        header(HttpHeaders.Authorization)
        allowCredentials = true
    }
    install(Authentication){
        jwt("auth-user") {
            realm = JwtComponent.realmUser
            verifier(JwtComponent.userJwtVerifier)
            validate { credential ->
                    JWTPrincipal(credential.payload)
            }
        }
        jwt ("auth-manager"){
            realm = JwtComponent.realmManager
            verifier(JwtComponent.managerJwtVerifier)
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