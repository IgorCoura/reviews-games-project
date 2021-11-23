package br.com.imt.dto

import kotlinx.serialization.Serializable

@Serializable
data class ReviewUserDTO (
    val id: Int,
    val review: String,
    val score: Int,
    val date: String,
    val game: GamesSimpleDTO,
    )