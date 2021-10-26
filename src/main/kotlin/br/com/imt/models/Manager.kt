package br.com.imt.models

import kotlinx.serialization.Serializable


data class Manager (
    val id: Int,
    val name: String,
    val email: String,
    val password: String
    )