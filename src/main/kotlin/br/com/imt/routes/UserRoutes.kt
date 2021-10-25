package br.com.imt.routes

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.userRouting(){
    route("/user"){
        get{
            call.respondText("Hello World")
        }
    }
}

fun Application.registerUserRoutes(){
    routing{
        userRouting()
    }
}