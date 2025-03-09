package com.fottow.fottow.domain.user.usecase

import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.user.repository.UserRepository

class RegisterUseCase(
    private val userRepository: UserRepository
) {

    suspend fun registerUser(userName: String, password: String, nickName: String): Result<Boolean, Error> {
        return userRepository.registerUser(userName, password, nickName)
    }
}