package br.com.imt

import br.com.imt.routes.registerGamesRoutes
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlin.text.get

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module(){
    install(ContentNegotiation){
        json()
    }
    registerGamesRoutes()
}