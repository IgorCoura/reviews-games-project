package br.com.imt.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserGamesDTO (
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val img: String?
    )