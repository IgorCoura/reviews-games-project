package br.com.imt.models

import kotlinx.serialization.Serializable

@Serializable
data class Review(
    val id: Int,
    val gameId: Int,
    val userId: Int,
    val review: String,
    val score: Int,
    val date: String
)