package br.com.imt.routes

import br.com.imt.dto.CreateGamesDTO
import br.com.imt.dto.GamesDTO
import br.com.imt.interfaces.IServiceGames
import br.com.imt.component.FileComponent
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.GamesRoutes(service: IServiceGames){
    route("/games"){
        authenticate("auth-manager") {
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
            delete("{id}") {
                val id = call.parameters["id"] ?: return@delete call.respondText(
                    "Missing or malformed id",
                    status = HttpStatusCode.BadRequest
                )
                service.delete(id)
                call.respondText("Game delete correctly", status = HttpStatusCode.NoContent)
            }
            post("/upload/{id}"){
                val multipartData = call.receiveMultipart()
                val id = call.parameters["id"] ?: return@post call.respondText(
                    "Missing or malformed id",
                    status = HttpStatusCode.BadRequest
                )
                val filaName = service.saveImg(multipartData, id)
                call.respondText("$filaName is uploaded")
            }
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
        get("/img/{id}"){
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing or malformed id",
                status = HttpStatusCode.BadRequest
            )
            val file = service.getImg(id)
            call.respondFile(file)
        }

    }
}

fun Application.registerGamesRoutes(service: IServiceGames){
    routing {
        GamesRoutes(service)
    }
}