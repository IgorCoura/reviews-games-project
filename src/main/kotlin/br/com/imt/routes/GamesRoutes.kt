package br.com.imt.routes

import br.com.imt.dao.GamesDAO
import br.com.imt.dao.IBaseDAO
import br.com.imt.dto.CreateGamesDTO
import br.com.imt.dto.GamesDTO
import br.com.imt.models.Games
import br.com.imt.models.Review
import br.com.imt.service.GamesService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.GamesRoutes(service: GamesService){
    route("/games"){
        post {
            val obj = call.receive<CreateGamesDTO>()
            service.insert(obj)
            call.respondText("Game stored correctly", status = HttpStatusCode.Created)
        }
        put {
            val obj = call.receive<GamesDTO>()
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

fun Application.registerGamesRoutes(service: GamesService){
    routing {
        GamesRoutes(service)
    }
}