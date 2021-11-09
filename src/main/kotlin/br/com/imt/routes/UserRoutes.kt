package br.com.imt.routes

import br.com.imt.JwtConfig
import br.com.imt.dto.*
import br.com.imt.interfaces.IServiceUser
import br.com.imt.models.User
import com.auth0.jwt.JWT
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
                val obj = call.receive<UpdateUserDTO>()
                service.update(obj)
                call.respondText("Game update correctly", status = HttpStatusCode.OK)
            }
            get{
                val principal = call.principal<JWTPrincipal>()
                val id =principal!!.payload.getClaim("id").toString()
                val obj = service.get(id)
                call.respond(obj)
            }
            get("/reviews"){
                val principal = call.principal<JWTPrincipal>()
                val id =principal!!.payload.getClaim("id").toString()
                val obj = service.getWithReviews(id)
                call.respond(obj)
            }
            delete() {
                val principal = call.principal<JWTPrincipal>()
                val id =principal!!.payload.getClaim("id").toString()
                service.delete(id)
                call.respondText("Game delete correctly", status = HttpStatusCode.NoContent)
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
                call.respondText("Game delete correctly", status = HttpStatusCode.NoContent)
            }
       }

    }
}

fun Application.registerUserRoutes(service: IServiceUser){
    routing{
        userRouting(service)
    }
}