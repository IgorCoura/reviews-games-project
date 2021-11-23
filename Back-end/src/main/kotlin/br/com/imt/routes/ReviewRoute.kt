package br.com.imt.routes

import br.com.imt.dto.*
import br.com.imt.interfaces.IServiceGames
import br.com.imt.interfaces.IServiceReview
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.ReviewRoutes(service: IServiceReview){
    route("/review"){
       authenticate("auth-user") {
            post {
                val obj = call.receive<CreateReviewDTO>()
                val principal = call.principal<JWTPrincipal>()
                val userId =principal!!.payload.getClaim("id").toString()
                service.insert(obj, userId)
                call.respondText("Review stored correctly", status = HttpStatusCode.Created)
            }
            put {
                val obj = call.receive<UpdateReviewDTO>()
                val principal = call.principal<JWTPrincipal>()
                val userId =principal!!.payload.getClaim("id").toString()
                service.update(obj, userId)
                call.respondText("Review update correctly", status = HttpStatusCode.OK)
            }
            get("{id}") {
                val id = call.parameters["id"] ?: return@get call.respondText(
                    "Missing or malformed id",
                    status = HttpStatusCode.BadRequest
                )
                val principal = call.principal<JWTPrincipal>()
                val userId =principal!!.payload.getClaim("id").toString()
                val obj = service.get(id, userId)
                call.respond(obj)
            }
            delete("{id}") {
                val id = call.parameters["id"] ?: return@delete call.respondText(
                    "Missing or malformed id",
                    status = HttpStatusCode.BadRequest
                )
                val principal = call.principal<JWTPrincipal>()
                val userId =principal!!.payload.getClaim("id").toString()
                service.delete(id, userId)
                call.respondText("Review delete correctly", status = HttpStatusCode.NoContent)
            }
        }
    }
}

fun Application.registerReviewRoutes(service: IServiceReview){
    routing {
        ReviewRoutes(service)
    }
}