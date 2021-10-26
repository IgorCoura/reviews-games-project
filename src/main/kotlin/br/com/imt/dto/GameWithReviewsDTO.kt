package br.com.imt.dto

import br.com.imt.models.Review
import kotlinx.serialization.Serializable

@Serializable
data class GameWithReviewsDTO (
    val id: Int,
    val name: String,
    val summary: String,
    val developer: String,
    val genre: String,
    val score: Int,
    val img: String?,
    val release: String,
    val consoles: String,
    ){
    var reviews= listOf<ReviewDTO>()

}