package br.com.imt.routes

import br.com.imt.dto.CreateGamesDTO
import br.com.imt.dto.GamesDTO
import br.com.imt.interfaces.IServiceGames
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.io.File

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
        }
        post("/upload"){
            val multipartData = call.receiveMultipart()
            var fileDescription = ""
            var fileName = ""
            multipartData.forEachPart { part ->
                when (part) {
                    is PartData.FormItem -> {
                        fileDescription = part.value
                    }
                    is PartData.FileItem -> {
                        fileName = part.originalFileName as String
                        var fileBytes = part.streamProvider().readBytes()
                        File("img/games/$fileName").writeBytes(fileBytes)
                    }
                }
            }
            call.respondText("$fileDescription is uploaded")
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

    }
}

fun Application.registerGamesRoutes(service: IServiceGames){
    routing {
        GamesRoutes(service)
    }
}