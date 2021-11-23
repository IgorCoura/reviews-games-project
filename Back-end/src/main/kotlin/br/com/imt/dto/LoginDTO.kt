package br.com.imt.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginDTO(
    val email: String,
    val password: String
) {
}