package br.com.imt.routes

import br.com.imt.dto.CreateUserDTO
import br.com.imt.dto.LoginDTO
import br.com.imt.dto.UpdateUserDTO
import br.com.imt.interfaces.IServiceUser
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

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
               val filaName = service.saveImg(multipartData, id)
               call.respondText("$filaName is uploaded")
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

fun Application.registerUserRoutes(service: IServiceUser){
    routing{
        userRouting(service)
    }
}