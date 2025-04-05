package com.fottow.fottow.domain.user.repository

import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Result

interface UserRepository {

    suspend fun logUser(user: String, password: String): Result<Boolean, Error>
    suspend fun registerUser(user: String, password: String, nickName: String): Result<Boolean, Error>
    suspend fun isUserLogged(): Result<String, Error>
    suspend fun logout(): Result<Boolean, Error>
}