package br.com.imt.dto

import kotlinx.serialization.Serializable

@Serializable
data class GamesSimpleDTO (
    val id: Int,
    val name: String,
    )