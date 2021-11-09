package br.com.imt.service

import br.com.imt.JwtConfig
import br.com.imt.dto.*
import br.com.imt.interfaces.IDaoUser
import br.com.imt.interfaces.IServiceUser
import br.com.imt.models.User
import java.math.BigInteger
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.*

class UserService(val dao: IDaoUser): IServiceUser {

    private val RANDOM: Random = SecureRandom();

    override fun login(login: LoginDTO): String{
        val salt = dao.getSalt(login.email)
        val hash = md5Hash(login.password+salt)
        val user = dao.get(login.email, hash)
        return JwtConfig.userGenerateToken(user)
    }

    override fun insert(obj: CreateUserDTO) {
        //Generate Salt
        val s= ByteArray(16)
        RANDOM.nextBytes(s)
        val salt = s.toString()
        //Generate Hash
        val hash = md5Hash(obj.password+salt)
        val user = User(0, obj.name, obj.email, hash, obj.img,salt)
        dao.insert(user)
    }

    override fun update(obj: UpdateUserDTO) {
        //Generate Salt
        val s= ByteArray(16)
        RANDOM.nextBytes(s)
        val salt = String.format("%016x", s)
        val user = User(obj.id, obj.name, obj.email, obj.password, obj.img, salt)
        dao.update(user)
    }

    override fun getAll(): List<UserDTO> {
        val users = dao.getAll()
        return users.map { p-> UserDTO(p.id,p.name,p.email) }
    }

    override fun get(id: String): UserDTO {
        val user = dao.get(id.toInt())
        return UserDTO(user.id, user.name, user.email)
    }

    override fun getWithReviews(id: String): UserWithReviewsDTO {
        val user = dao.getWithReviews(id.toInt())
        val userDTO = UserWithReviewsDTO(user.id, user.name, user.email)
        userDTO.reviews = user.reviews.map { r -> ReviewUserDTO(r.id, r.gameId, r.userId, r.review, r.score, r.date, GamesSimpleDTO(r.game!!.id, r.game!!.name) )}
        return userDTO
    }

    override fun delete(id: String) {
        dao.delete(id.toInt())
    }

    fun md5Hash(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }

}