package br.com.imt.routes

import br.com.imt.dto.CreateUserDTO
import br.com.imt.dto.LoginDTO
import br.com.imt.dto.UpdateUserDTO
import br.com.imt.interfaces.IServiceUser
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths

fun Route.userRouting(service: IServiceUser){
    route("/user"){
        post("/login"){
            val obj = call.receive<LoginDTO>()
            val token = service.login(obj)
            call.respond(hashMapOf("token" to token))
        }
        post {
            val obj = call.receive<CreateUserDTO>()
            service.insert(obj)
            call.respondText("User stored correctly", status = HttpStatusCode.Created)
        }

       authenticate("auth-user") {
           put {
               val principal = call.principal<JWTPrincipal>()
               val id = principal!!.payload.getClaim("id").toString()
               val obj = call.receive<UpdateUserDTO>()
               service.update(obj, id)
               call.respondText("Game update correctly", status = HttpStatusCode.OK)
           }
           get {
               val principal = call.principal<JWTPrincipal>()
               val id = principal!!.payload.getClaim("id").toString()
               val obj = service.get(id)
               call.respond(obj)
           }
           get("/reviews") {
               val principal = call.principal<JWTPrincipal>()
               val id = principal!!.payload.getClaim("id").toString()
               val obj = service.getWithReviews(id)
               call.respond(obj)
           }
           delete() {
               val principal = call.principal<JWTPrincipal>()
               val id = principal!!.payload.getClaim("id").toString()
               service.delete(id)
               call.respondText("User delete correctly", status = HttpStatusCode.NoContent)
           }

           post("/upload") {
               val multipartData = call.receiveMultipart()
               val principal = call.principal<JWTPrincipal>()
               val id = principal!!.payload.getClaim("id").toString()
               var fileDescription = ""
               var fileName = ""
               multipartData.forEachPart { part ->
                   when (part) {
                       is PartData.FormItem -> {
                           fileDescription = part.value
                       }
                       is PartData.FileItem -> {
                           val root = System.getProperty("user.dir") + "/img"
                           fileName = part.originalFileName as String
                           val filePath = Paths.get(root, "/users/$id")
                           Files.createDirectories(filePath)
                           var fileBytes = part.streamProvider().readBytes()
                           File("$filePath/$fileName").writeBytes(fileBytes)
                       }
                   }
               }
               call.respondText("$fileDescription is uploaded")
           }
       }

        authenticate("auth-manager"){
            get("{id}"){
                val id = call.parameters["id"] ?: return@get call.respondText(
                    "Missing or malformed id",
                    status = HttpStatusCode.BadRequest
                )
                val obj = service.get(id)
                call.respond(obj)
            }

            get("/all"){
                val obj = service.getAll()
                call.respond(obj)
            }

            delete("{id}") {
                val id = call.parameters["id"] ?: return@delete call.respondText(
                    "Missing or malformed id",
                    status = HttpStatusCode.BadRequest
                )
                service.delete(id)
                call.respondText("User delete correctly", status = HttpStatusCode.NoContent)
            }
       }

    }
}

fun Application.registerUserRoutes(service: IServiceUser){
    routing{
        userRouting(service)
    }
}