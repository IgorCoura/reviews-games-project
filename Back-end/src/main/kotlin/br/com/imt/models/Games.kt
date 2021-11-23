package br.com.imt.models

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
    var reviews = mutableListOf<Review>()
}
