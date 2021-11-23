package br.com.imt.routes

import br.com.imt.JwtConfig
import br.com.imt.dto.LoginDTO
import br.com.imt.models.Manager
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.ManagerRoutes(){
    route("/manager"){
        post {
            val obj = call.receive<LoginDTO>()
            if (obj.email == "root" && obj.password=="root"){
                val token = JwtConfig.managerGenerateToken(Manager(1, "Root", "root", "root"))
                call.respond(hashMapOf("token" to token))
            }
            else{
                call.respondText("Incorrect email or password", status = HttpStatusCode.Unauthorized)
            }
        }
    }
}

fun Application.registerManageRoutes(){
    routing {
        ManagerRoutes()
    }
}