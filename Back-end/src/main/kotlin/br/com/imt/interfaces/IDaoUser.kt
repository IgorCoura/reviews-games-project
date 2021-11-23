package br.com.imt.interfaces

import br.com.imt.dto.LoginDTO
import br.com.imt.models.Review
import br.com.imt.models.User
import java.sql.DriverManager

interface IDaoUser {
    fun insert(obj: User)
    fun update(obj: User)
    fun delete(id: Int)
    fun get(id: Int): User
    fun get(email:String, password: String): User
    fun getAll(): List<User>
    fun getWithReviews(id: Int): User
    fun getSalt(email:String): String
}