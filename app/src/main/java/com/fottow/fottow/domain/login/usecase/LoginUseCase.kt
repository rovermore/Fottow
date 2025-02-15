package com.fottow.fottow.domain.login.usecase

import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.login.repository.LoginRepository

class LoginUseCase(
    private val loginRepository: LoginRepository
) {

    suspend fun logUser(userName: String, password: String): Result<Boolean, Error> {
        return loginRepository.logUser(userName, password)
    }
}