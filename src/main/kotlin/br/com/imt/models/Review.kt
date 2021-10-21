package br.com.imt.models

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val id: Int,
    val game_id: Int,
    val user_id: Int,
    val review: String,
    val score: Int,
    val data: String
)