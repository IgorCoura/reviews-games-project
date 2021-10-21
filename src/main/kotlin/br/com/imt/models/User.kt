package br.com.imt.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val password: String,
    val img: String,
    val reviews: List<Review>
)