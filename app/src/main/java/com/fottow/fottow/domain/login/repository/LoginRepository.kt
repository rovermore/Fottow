package com.fottow.fottow.domain.login.repository

import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result

interface LoginRepository {

    suspend fun logUser(user: String, password: String): Result<Boolean, Error>
}