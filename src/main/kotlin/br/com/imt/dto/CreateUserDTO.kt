package br.com.imt.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserDTO (
    val name: String,
    val email: String,
    val password: String,
    val img: String?,
    )