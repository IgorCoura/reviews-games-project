package br.com.imt.routes

import br.com.imt.dto.CreateGamesDTO
import br.com.imt.dto.CreateUserDTO
import br.com.imt.dto.GamesDTO
import br.com.imt.dto.UpdateUserDTO
import br.com.imt.interfaces.IServiceUser
import br.com.imt.service.UserService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.userRouting(service: IServiceUser){
    route("/user"){
        post {
            val obj = call.receive<CreateUserDTO>()
            service.insert(obj)
            call.respondText("Game stored correctly", status = HttpStatusCode.Created)
        }
        put {
            val obj = call.receive<UpdateUserDTO>()
            service.update(obj)
            call.respondText("Game update correctly", status = HttpStatusCode.OK)
        }
        get{
            val obj = service.getAll()
            call.respond(obj)
        }
        get("{id}"){
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val obj = service.get(id)
            call.respond(obj)
        }
        get("reviews/{id}"){
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val obj = service.getWithReviews(id)
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

fun Application.registerUserRoutes(service: IServiceUser){
    routing{
        userRouting(service)
    }
}