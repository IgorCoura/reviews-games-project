package br.com.imt.dto

import kotlinx.serialization.Serializable

@Serializable
data class GamesDTO (
    val id: Int,
    val name: String,
    val summary: String,
    val developer: String,
    val genre: String,
    val score: Int,
    val img: String?,
    val release: String,
    val consoles: String
    )