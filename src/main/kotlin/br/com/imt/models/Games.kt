package br.com.imt.models

import kotlinx.serialization.Serializable


@Serializable
data class Games(
    val id: Int,
    val name: String,
    val summary: String,
    val developer: String,
    val genre: String,
    val score: Int,
    val img: String,
    val release: String,
    val consoles: String,
    val reviews: List<Review>
        )
