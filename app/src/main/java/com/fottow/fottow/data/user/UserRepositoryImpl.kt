package com.fottow.fottow.data.user

import com.fottow.fottow.data.base.APIErrorMapper
import com.fottow.fottow.data.user.local.UserLocalDatasource
import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.base.Success
import com.fottow.fottow.domain.base.get
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
        val user = User()
         userLocalDatasource.getEmail().get()
        userLocalDatasource.getName().get()
        return Success(user)
    }
}