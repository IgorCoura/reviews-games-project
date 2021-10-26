package br.com.imt.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO (
    val id: Int,
    val name: String,
    val email: String,
    val img: String?
    )