package com.fottow.fottow.domain.user.usecase

import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.user.repository.UserRepository

class LoginUseCase(
    private val userRepository: UserRepository
) {

    suspend fun logUser(userName: String, password: String): Result<Boolean, Error> {
        return userRepository.logUser(userName, password)
    }
}