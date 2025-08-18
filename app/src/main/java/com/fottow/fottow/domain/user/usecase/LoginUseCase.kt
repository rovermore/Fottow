package com.fottow.fottow.domain.user.usecase

import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.user.model.User
import com.fottow.fottow.domain.user.repository.UserRepository

class LoginUseCase(
    private val userRepository: UserRepository
) {

    suspend fun logUser(userName: String, password: String): Result<User, Error> {
        return userRepository.logUser(userName, password)
    }

    suspend fun isUserLogged(): Result<String, Error> {
        return userRepository.isUserLogged()
    }

    suspend fun logout(): Result<Boolean, Error> {
        return userRepository.logout()
    }

    suspend fun isFirstInstall(): Result<Boolean, Error> {
        return userRepository.isFirstInstall()
    }
}