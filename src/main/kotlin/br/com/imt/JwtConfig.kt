package br.com.imt

import br.com.imt.models.Manager
import br.com.imt.models.User
import com.auth0.jwk.JwkProviderBuilder
import com.auth0.jwt.JWT

import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*
import java.util.concurrent.TimeUnit

object JwtConfig {
    private const val secret = "supersegredo"
    private const val issuer = "http://0.0.0.0:8080/"
    const val userAudience = "http://0.0.0.0:8080/user"
    const val managerAudience = "http://0.0.0.0:8080/manager"
    const val realmUser = "Access to user"
    const val realmManager = "Access to manager"

    fun userGenerateToken(user: User): String{
        val token = JWT.create()
            .withAudience(userAudience)
            .withIssuer(issuer)
            .withClaim("id", user.id)
            .withClaim("name", user.name)
            .withClaim("email", user.email)
            .withExpiresAt(Date(System.currentTimeMillis() + 60000))
            .sign(Algorithm.HMAC256(secret))
        return  token
    }

    fun managerGenerateToken(manager: Manager): String{
        val token = JWT.create()
            .withAudience(managerAudience)
            .withIssuer(issuer)
            .withClaim("id", manager.id)
            .withClaim("name", manager.name)
            .withClaim("email", manager.email)
            .withExpiresAt(Date(System.currentTimeMillis() + 60000))
            .sign(Algorithm.HMAC256(secret))
        return  token
    }

    val userJwtVerifier = JWT
        .require(Algorithm.HMAC256(secret))
        .withAudience(userAudience)
        .withIssuer(issuer)
        .build()

    val managerJwtVerifier = JWT
        .require(Algorithm.HMAC256(secret))
        .withAudience(managerAudience)
        .withIssuer(issuer)
        .build()

}