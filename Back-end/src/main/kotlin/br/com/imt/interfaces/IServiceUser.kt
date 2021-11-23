package br.com.imt.interfaces

import br.com.imt.dto.*

interface IServiceUser {
    fun login(login: LoginDTO): String
    fun insert(obj: CreateUserDTO)
    fun update(obj: UpdateUserDTO, id: String)
    fun getAll(): List<UserDTO>
    fun get(id: String): UserDTO
    fun getWithReviews(id: String): UserWithReviewsDTO
    fun delete(id: String)
}