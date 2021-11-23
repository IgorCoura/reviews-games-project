package br.com.imt.models

data class Review(
    val id: Int,
    val gameId: Int,
    val userId: Int,
    val review: String,
    val score: Int,
    val date: String,
    var game: Games? = null,
    var user: User? = null
){

}