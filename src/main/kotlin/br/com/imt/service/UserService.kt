package br.com.imt.service

import br.com.imt.dao.UserDAO
import br.com.imt.dto.*
import br.com.imt.interfaces.IDaoUser
import br.com.imt.interfaces.IServiceUser
import br.com.imt.models.User

class UserService(val dao: IDaoUser): IServiceUser {

    override fun insert(obj: CreateUserDTO) {
        val user = User(0, obj.name, obj.email, obj.password, obj.img)
        dao.insert(user)
    }

    override fun update(obj: UpdateUserDTO) {
        val user = User(obj.id, obj.name, obj.email, obj.password, obj.img)
        dao.update(user)
    }

    override fun getAll(): List<UserDTO> {
        val users = dao.getAll()
        return users.map { p-> UserDTO(p.id,p.name,p.email,p.img) }
    }

    override fun get(id: String): UserDTO {
        val user = dao.get(id.toInt())
        return UserDTO(user.id, user.name, user.email, user.img)
    }

    override fun getWithReviews(id: String): UserWithReviewsDTO {
        val user = dao.getWithReviews(id.toInt())
        val userDTO = UserWithReviewsDTO(user.id, user.name, user.email, user.img)
        userDTO.reviews = user.reviews.map { r -> ReviewDTO(r.id, r.gameId, r.userId, r.review, r.score, r.date)  }
        return userDTO
    }

    override fun delete(id: String) {
        dao.delete(id.toInt())
    }


}