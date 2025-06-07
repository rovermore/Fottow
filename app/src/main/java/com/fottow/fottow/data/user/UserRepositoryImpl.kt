package com.fottow.fottow.data.user

import com.fottow.fottow.data.base.APIErrorMapper
import com.fottow.fottow.data.user.local.UserLocalDatasource
import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.base.Success
import com.fottow.fottow.domain.base.getOrDefault
import com.fottow.fottow.domain.base.map
import com.fottow.fottow.domain.base.mapFailure
import com.fottow.fottow.domain.user.model.User
import com.fottow.fottow.domain.user.repository.UserRepository

class UserRepositoryImpl(
    private val userNetworkDatasource: UserNetworkDatasource,
    private val userLocalDatasource: UserLocalDatasource,
    private val apiErrorMapper: APIErrorMapper
): UserRepository {
    override suspend fun logUser(user: String, password: String): Result<Boolean, Error> {
        return userNetworkDatasource.logUser(user, password)
            .map {
                userLocalDatasource.setToken(it.token)
                userLocalDatasource.setEmail(user)
                it.userName.nick?.let { it1 -> userLocalDatasource.setName(it1) }
                true
            }
            .mapFailure {
                apiErrorMapper.map(it)
            }
    }

    override suspend fun registerUser(
        user: String,
        password: String,
        nickName: String
    ): Result<Boolean, Error> {
        return userNetworkDatasource.userRegister(user, password, nickName)
            .map {
                userLocalDatasource.setToken(it.token)
                userLocalDatasource.setName(nickName)
                userLocalDatasource.setEmail(user)
                true
            }
            .mapFailure {
                apiErrorMapper.map(it)
            }
    }

    override suspend fun isUserLogged(): Result<String, Error> {
        return userLocalDatasource.getToken()
    }

    override suspend fun logout(): Result<Boolean, Error> {
        return userNetworkDatasource.logout()
            .map {
                userLocalDatasource.deleteToken()
                userLocalDatasource.deleteEmail()
                userLocalDatasource.deleteName()
                true
            }
            .mapFailure {
                apiErrorMapper.map(it)
            }
    }

    override suspend fun getUser(): Result<User, Error> {
        val email = userLocalDatasource.getEmail().getOrDefault("")
        val name = userLocalDatasource.getName().getOrDefault("")

        val user = User(email, name)
        return Success(user)
    }
}
