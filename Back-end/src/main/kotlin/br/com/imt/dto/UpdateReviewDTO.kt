package br.com.imt.dto

import kotlinx.serialization.Serializable

@Serializable
data class UpdateReviewDTO (
    val id: Int,
    val gameId: Int,
    val review: String,
    val score: Int,
    val date: String
    )