package br.com.imt.interfaces

import br.com.imt.dto.*
import br.com.imt.models.User
import io.ktor.http.content.*
import java.io.File

interface IServiceUser {
    fun login(login: LoginDTO): String
    fun insert(obj: CreateUserDTO)
    fun update(obj: UpdateUserDTO, id: String)
    fun getAll(): List<UserDTO>
    fun get(id: String): UserDTO
    fun getWithReviews(id: String): UserWithReviewsDTO
    fun delete(id: String)
    fun getImg(id: String): File
    suspend fun saveImg(multipartData: MultiPartData, id: String): String
}