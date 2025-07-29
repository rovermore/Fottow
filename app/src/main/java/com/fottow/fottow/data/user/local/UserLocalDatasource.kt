package com.fottow.fottow.data.user.local

import com.fottow.fottow.domain.base.Error
import com.fottow.fottow.domain.base.Failure
import com.fottow.fottow.domain.base.Result
import com.fottow.fottow.domain.base.Success
import com.fottow.fottow.domain.user.model.User
import kotlinx.serialization.json.Json

class UserLocalDatasource(
    private val dataStore: FottowDataStore
) {

    companion object {
        const val TOKEN_KEY = "TOKEN_KEY"
        const val EMAIL_KEY = "EMAIL_KEY"
        const val NAME_KEY = "NAME_KEY"
        const val USER_KEY = "USER_KEY"
    }

    private val json = Json // singleton de kotlinx.serialization

    fun getToken(): Result<String, Error> = dataStore.read<String>(TOKEN_KEY)

    fun setToken(tkn: String) {
        dataStore.save(TOKEN_KEY, tkn)
    }

    fun deleteToken() {
        dataStore.delete<String>(TOKEN_KEY)
    }

    fun getEmail(): Result<String, Error> = dataStore.read<String>(EMAIL_KEY)

    fun setEmail(email: String) {
        dataStore.save(EMAIL_KEY, email)
    }

    fun deleteEmail() {
        dataStore.delete<String>(EMAIL_KEY)
    }

    fun getName(): Result<String, Error> = dataStore.read<String>(NAME_KEY)

    fun setName(name: String) {
        dataStore.save(NAME_KEY, name)
    }

    fun deleteName() {
        dataStore.delete<String>(NAME_KEY)
    }

    fun setUser(user: User) {
        val userString = json.encodeToString(user)
        dataStore.save(USER_KEY, userString)
    }

    fun getUser(): Result<User, Error> {
        return when (val result = dataStore.read<String>(USER_KEY)) {
            is Success -> {
                val user = try {
                    json.decodeFromString<User>(result.value)
                } catch (e: Exception) {
                    return Failure(Error.UncompletedOperation("Invalid user data"))
                }
                Success(user)
            }
            is Failure -> Failure(result.reason)
        }
    }

    fun deleteUser() {
        dataStore.delete<String>(USER_KEY)
    }
}