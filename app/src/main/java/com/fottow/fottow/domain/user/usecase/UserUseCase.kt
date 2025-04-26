package com.fottow.fottow.domain.user.usecase

import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.user.model.User
import com.fottow.fottow.domain.user.repository.UserRepository

class UserUseCase(
    private val userRepository: UserRepository
) {

    suspend fun getUser(): Result<User, Error> =
        userRepository.getUser()
}