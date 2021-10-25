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
    val img: String?,
    val release: String,
    val consoles: String,
        ){
    var reviews: List<Review>?
        get() {
            return this.reviews
        }
        set(value) {
            this.reviews = value
        }

}
