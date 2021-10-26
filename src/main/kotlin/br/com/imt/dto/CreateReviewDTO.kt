package br.com.imt.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateReviewDTO (
    val gameId: Int,
    val userId: Int,
    val review: String,
    val score: Int,
    val date: String
        )