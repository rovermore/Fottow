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
    override suspend fun logUser(user: String, password: String): Result<User, Error> {
        return userNetworkDatasource.logUser(user, password)
            .map {
                userLocalDatasource.setToken(it.token)
                val receivedUser = User(
                    email = it.userData.userName,
                    name = it.userData.nick ?: "",
                    profileImage = it.userData.profileImage ?: ""
                )
                userLocalDatasource.setUser(receivedUser)
                receivedUser
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
                userLocalDatasource.setUser(
                    User(
                        email = user,
                        name = nickName,
                        profileImage = ""
                    )
                )
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
                userLocalDatasource.deleteUser()
                true
            }
            .mapFailure {
                apiErrorMapper.map(it)
            }
    }

    override suspend fun getUser(): Result<User, Error> {
        return userLocalDatasource.getUser()
    }
}
