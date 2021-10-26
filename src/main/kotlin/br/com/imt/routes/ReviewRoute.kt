package br.com.imt.routes

import br.com.imt.dto.CreateGamesDTO
import br.com.imt.dto.CreateReviewDTO
import br.com.imt.dto.GamesDTO
import br.com.imt.dto.ReviewDTO
import br.com.imt.interfaces.IServiceGames
import br.com.imt.interfaces.IServiceReview
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.ReviewRoutes(service: IServiceReview){
    route("/review"){
        post {
            val obj = call.receive<CreateReviewDTO>()
            service.insert(obj)
            call.respondText("Review stored correctly", status = HttpStatusCode.Created)
        }
        put {
            val obj = call.receive<ReviewDTO>()
            service.update(obj)
            call.respondText("Review update correctly", status = HttpStatusCode.OK)
        }
        get("{id}"){
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val obj = service.get(id)
            call.respond(obj)
        }
        delete("{id}") {
            val id = call.parameters["id"] ?: return@delete call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            service.delete(id)
            call.respondText("Game delete correctly", status = HttpStatusCode.NoContent)
        }
    }
}

fun Application.registerReviewRoutes(service: IServiceReview){
    routing {
        ReviewRoutes(service)
    }
}